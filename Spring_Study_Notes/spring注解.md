## spring注解

### 常用注解

- 创建对象
- 相当于`<bean></bean>`标签
	- `@Component`：
		- 作用：用于把当前类对象存入spring容器中
		- 属性：
			- `value`：用于指定bean的id，默认值是当前类名首字母小写
	- `@Controller`：表现层
	- `@Service`：业务层
	- `@Repository`：持久层
- 注入数据

	- 相当于`<bean>`标签中的`<property/>`
	- `@AutoWired`：
		- 作用：自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功。如果`Ioc`容器中没有任何bean的类型和要注入的变量类型匹配，则报错
		- 出现位置：可以是变量上，也可以是方法上
		- 细节：在使用注解时，set方法就不是必须的
	- `@Qualifer`：
		- 作用：再按照类注入的基础上再按照名称注入。它在给类成员注入时不能单独使用，但是给方法参数注入时可以。
		- 属性：
			- `value`：用于指定注入bean的id
	- `@Resource`：
		- 作用：直接按照bean的id注入，可以单独使用
		- 属性：
			- `name`：用于指定bean的id
	- 以上三个注入都只能注入其他bean类型的数据，无法注入基本类型和String类型。
	- `@Value`：
		- 作用：用于注入基本类型和String类型的数据
		- 属性：
			- `value`：用于指定数据的值，它可以使用spring中的`SpEL`（spring中的EL表达式）：`${表达式}`
	- 集合类型的注入只能通过XML来实现
- 作用范围

	- `@Scope`：
		- 作用：用于指定bean的作用范围
		- 属性：
			- `value`：指定范围的取值，默认单例
- 生命周期相关（了解）

	- `@PostConstruct`：
		- 作用：用于指定初始化方法
	- `@PreDestroy`：
		- 作用：用于指定销毁的方法
- 

```java
@Component(value = "accountService")
public class AccountServiceImpl implements AccountService {

    // @Autowired
    // @Qualifier("accountDao2")

    @Resource(name = "accountDao2")
    private AccountDao dao;

    /**
     * 保存帐户
     */
    @Override
    public void saveAccount() {
        dao.saveAccount();
        System.out.println(dao);
    }
}
```

- 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 告知在创建容器时要扫描的包 -->
    <context:component-scan base-package="com.ce"/>
</beans>
```

### 新注解(无bean.xml文件)

- `@Configuration`：

	- 作用：指定当前类是一个配置类
	- 细节：当配置类作为`AnnotationConfigApplicationContext`创建的参数时，可以不写

- `@ComponentScan`：

	- 作用：用于通过注解指定spring创建容器扫描的包
	- 属性：
		- `value`：同`basePackages`作用是一样的，用于指定创建容器时要扫描的包。
	- 使用此注解就等同于`<context:component-scan base-package="com.ce"/>`

- `@Bean`：

	- 作用：用于把当前方法的返回值作为bean对象存入spring的Ioc容器中。
	- 属性：
		- `name`：用于指定bean的id，默认值是当前方法名
	- 细节：
		- 当使用注解配置方法时，如果方法有参数，spring框架就会去容器中查找有没有可用的bean对象，查找方式同`@AutoWired`

- `@Import`：

	- 作用：
		- 导入其他配置类
	- 属性：
		- `value`：用于指定其他配置类的字节码
	- 当我们使用`@Import`注解后，导入的为子配置类

- `@PropertySource`：

	- 作用：
		- 用于指定properties文件的位置
	- 属性：
		- `value`：指定文件的名称和路径
			- 关键字  `classpath:`表示类路径下

- ```java
	@Configuration
	@ComponentScan("com.ce")
	@Import(JdbcConfig.class)
	@PropertySource("classpath:jdbcConfig.properties")
	public class SpringConfiguration {
	
	}
	```

- ```java
	@Configuration
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
	     * 用于创建一个QueryRunner对象
	     */
	    @Bean(name = "runner")
	    @Scope("prototype")  //多例
	    public QueryRunner createQueryRunner(@Qualifier("dataSource") DataSource dataSource) {
	        return new QueryRunner(dataSource);
	    }
	
	    @Bean("dataSource")
	    public DataSource createDataSource() {
	        try {
	            ComboPooledDataSource ds = new ComboPooledDataSource();
	            ds.setDriverClass(driver);
	            ds.setJdbcUrl(url);
	            ds.setUser(username);
	            ds.setPassword(password);
	            return ds;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	}
	```

- ```java
	ApplicationContext ac=new AnnotationConfigApplicationContext(SpringConfiguration.class);
	```

### Spring整合Junit

1. 导入spring整合junit的包 `spring-test`
2. 使用Junit提供的一个注解把原有的main方法替换成spring提供的
	- Junit4  `@RunWith(SpringJUnit4ClassRunner.class)`
	- Junit5 `@ExtendWith(SpringExtension.class)`
3. 告知spring运行时，spring和Ioc创建是基于xml还是注解的，并且告知xml文件位置
	- `@ContextConfiguration`
		- `locations`：：指定xml文件位置
		- `classes`：指定注解类位置
	- 基于XML：`@ContextConfiguration(locations = "classpath:bean.xml")`
	- 基于注解：`@ContextConfiguration(classes = SpringConfiguration.class)`
4. 注入

- ```java
	// @RunWith(SpringJUnit4ClassRunner.class)  //junit4
	@ExtendWith(SpringExtension.class)  //junit5
	@ContextConfiguration(classes = SpringConfiguration.class)
	public class AccountSerrviceTest {
	
	    @Autowired
	    private AccountService service;
	
	    @Test
	    public void testFindAll() {
	        List<Account> accounts = service.findAllAccount();
	        accounts.forEach(System.out::println);
	    }
	
	    @Test
	    public void testFindOne() {
	        Account account = service.findAccountById(1);
	        System.out.println(account);
	    }
	
	    @Test
	    public void testSave() {
	        Account account = new Account();
	        account.setName("张三");
	        account.setMoney(1122.0f);
	        service.saveAccount(account);
	        testFindAll();
	    }
	
	    @Test
	    public void testUpdate() {
	        Account account = service.findAccountById(4);
	        account.setMoney(12580.0f);
	        service.updateAccount(account);
	        testFindAll();
	    }
	
	    @Test
	    public void testDel() {
	        service.deleteAccount(5);
	    }
	}
	```

	