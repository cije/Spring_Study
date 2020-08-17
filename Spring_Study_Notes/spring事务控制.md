# Spring声明式事务

## 基于XML

- 步骤：

	1. 配置事务管理器
	2. 配置事务通知
		- 此时需要导入tx和aop的约束
		- 使用`tx:advice`标签配置事务通知
			- 属性：
				- `id`：给事务通知起一个唯一标识
				- `transaction-manager`：给事务通知提供一个事务管理器
	3. 配置AOP的通用切入点表达式
	4. 建立事务通知和切入点表达式的关系 `aop:advisor`
	5. 配置事物的属性（在事务的通知`tx:advisor`标签的内部）
		- `isolation`：用于指定事务的隔离级别，默认值式DEFAULT，表示使用数据库的默认隔离级别            
		- `propagation`：指定事务的传播行为，默认值为REQUIRED，表示一定会有事务，增删改的选择，。查询方法可以使用SUPPORTS           
		- `read-only`：用于指定事务是否只读。只有查询方法才能设置为true。默认值false，表示可读写           
		- `timeout`：指定事务的超时事件，默认值为-1，表示永不超            
		- `rollback-for`：用于指定一个异常，当产生该异常时，事务回滚，产生其他异常时，事务不回滚。没有默认值，表示任何异常都回滚            
		- `no-rollback-for`：用于指定一个异常，当产生该异常时事务不回滚，产生其他异常时回滚。没有默认值，表示任何异常都回滚

- ```xml
	<!-- 配置事务管理器 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<!-- 配置事务通知 -->
	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<!--配置事务的属性-->
		<tx:attributes>
			<tx:method name="transfer" propagation="REQUIRED"/>
			<tx:method name="find*" propagation="SUPPORTS"/>
		</tx:attributes>
	</tx:advice>
	
	<!--配置AOP-->
	<aop:config>
		<aop:pointcut id="pt1" expression="execution(* com.ce.service.impl.*.*(..))"/>
		<!--建立切入点表达式和事务通知的对应关系-->
		<aop:advisor advice-ref="txAdvice" pointcut-ref="pt1"/>
	</aop:config>
	```

## 基于注解

- 步骤：

	1. 配置事务管理器
	2. 开启spring对注解事务的支持 `tx:annotation-driven`
	3. 在需要事务支持的地方使用`@Transactional`注解

- ```xml
	<!-- 配置事务管理器 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<!--开启spring对注解事务的支持-->
	<tx:annotation-driven transaction-manager="transactionManager"/>
	```

- ```java
	/**
	 * 账户的业务层实现类
	 */
	@Service("accountService")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) //只读型事务的配置
	public class AccountServiceImpl implements AccountService {
	    @Autowired
	    private AccountDao accountDao;
	
	    /**
	     * 根据id查找
	     *
	     * @param id
	     */
	    @Override
	    public Account findAccountById(Integer id) {
	        return accountDao.findAccountById(id);
	    }
	
	
	    /**
	     * 转账
	     *
	     * @param sourceName 转出账户名称
	     * @param targetName 转入账户名称
	     * @param money      转账金额
	     */
	    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) //事务的配置
	    @Override
	    public void transfer(String sourceName, String targetName, Float money) {
	        //1.根据名称查询转出账户
	        Account source = accountDao.findAccountByName(sourceName);
	        //2.根据名称查询转入账户
	        Account target = accountDao.findAccountByName(targetName);
	        //3.转出账户减钱
	        source.setMoney(source.getMoney() - money);
	        //4.转入账户加钱
	        target.setMoney(target.getMoney() + money);
	        //5.更新转出账户
	        accountDao.updateAccount(source);
	        int i = 1 / 0;
	        //6.更新转入账户
	        accountDao.updateAccount(target);
	    }
	}
	```

## 基于注解（无XML文件）

- 总配置类

	```java
	/**
	 * spring的配置类，相当于bean.xml
	 */
	@Configuration
	@ComponentScan("com.ce")
	@Import({JdbcConfig.class, TransactionConfig.class})
	@EnableTransactionManagement //开启事务注解支持
	public class SpringConfiguration {}
	```

- 数据库连接配置类

	```java
	/**
	 * 和连接数据库相关的配置
	 */
	@Configuration
	@PropertySource("classpath:JdbcConfig.properties")
	public class JdbcConfig {
	    @Value("${jdbc.driver}")
	    private String driver;
	    @Value("${jdbc.url}")
	    private String url;
	    @Value("${jdbc.username}")
	    private String username;
	    @Value("${jdbc.password}")
	    private String password;
	
	    /**
	     * 创建JdbcTEmplate对象
	     */
	    @Bean(name = "jdbcTemplate")
	    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
	        return new JdbcTemplate(dataSource);
	    }
	
	    @Bean(name = "dataSource")
	    public DataSource createDataSource() {
	        DriverManagerDataSource ds = new DriverManagerDataSource();
	        ds.setDriverClassName(driver);
	        ds.setUrl(url);
	        ds.setUsername(username);
	        ds.setPassword(password);
	        return ds;
	    }
	}
	```

- 事务相关的配置类

	```java
	/**
	 * 和事务相关的配置类
	 */
	@Configuration
	public class TransactionConfig {
	    /**
	     * 用于创建事务管理器对象
	     */
	    @Bean(name = "transactionManager")
	    public PlatformTransactionManager createTransactionManager(DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }
	}
	```

- ```java
	/**
	 * 账户的业务层实现类
	 */
	@Service("accountService")
	@Transactional(transactionManager = "transactionManager",propagation = Propagation.SUPPORTS,readOnly = true)
	public class AccountServiceImpl implements AccountService {
	    @Autowired
	    private AccountDao accountDao;
	
	    /**
	     * 根据id查找
	     *
	     * @param id
	     */
	    @Override
	    public Account findAccountById(Integer id) {
	        return accountDao.findAccountById(id);
	    }
	
	    /**
	     * 转账
	     *
	     * @param sourceName 转出账户名称
	     * @param targetName 转入账户名称
	     * @param money      转账金额
	     */
	    @Transactional(transactionManager = "transactionManager",propagation = Propagation.REQUIRED,readOnly = false)
	    @Override
	    public void transfer(String sourceName, String targetName, Float money) {
	        //1.根据名称查询转出账户
	        Account source = accountDao.findAccountByName(sourceName);
	        //2.根据名称查询转入账户
	        Account target = accountDao.findAccountByName(targetName);
	        //3.转出账户减钱
	        source.setMoney(source.getMoney() - money);
	        //4.转入账户加钱
	        target.setMoney(target.getMoney() + money);
	        //5.更新转出账户
	        accountDao.updateAccount(source);
	        // int i=1/0;
	        //6.更新转入账户
	        accountDao.updateAccount(target);
	    }
	}
	```

## 编程式事务（了解）

- ```xml
	<!-- spring编程式事务控制 -->
	
	<!--配置事务管理器-->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<!-- 事务模板对象-->
	<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
		<property name="transactionManager" ref="transactionManager"/>
	</bean>
	
	```

- ```java
	@Service("accountService")
	public class AccountServiceImpl implements AccountService {
	    @Autowired
	    private AccountDao accountDao;
	    @Autowired
	    private TransactionTemplate transactionTemplate;
	
	    /**
	     * 根据id查找
	     *
	     * @param id
	     */
	    @Override
	    public Account findAccountById(Integer id) {
	        return transactionTemplate.execute(new TransactionCallback<Account>() {
	            @Override
	            public Account doInTransaction(TransactionStatus transactionStatus) {
	                
	                return accountDao.findAccountById(id);
	                
	            }
	        });
	    }
	
	
	    /**
	     * 转账
	     *
	     * @param sourceName 转出账户名称
	     * @param targetName 转入账户名称
	     * @param money      转账金额
	     */
	    @Override
	    public void transfer(String sourceName, String targetName, Float money) {
	        transactionTemplate.execute(new TransactionCallback<Object>() {
	            @Override
	            public Object doInTransaction(TransactionStatus transactionStatus) {
	                
	                //1.根据名称查询转出账户
	                Account source = accountDao.findAccountByName(sourceName);
	                //2.根据名称查询转入账户
	                Account target = accountDao.findAccountByName(targetName);
	                //3.转出账户减钱
	                source.setMoney(source.getMoney() - money);
	                //4.转入账户加钱
	                target.setMoney(target.getMoney() + money);
	                //5.更新转出账户
	                accountDao.updateAccount(source);
	                // int i = 1 / 0;
	                //6.更新转入账户
	                accountDao.updateAccount(target);
	                return null;
	                
	            }
	        });
	
	    }
	}
	```

	



