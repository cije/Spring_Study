#### 创建Bean的三种方式：：:smile:

 1.  使用默认构造函数创建

	- 在spring的配置文件中使用bean标签，配以id和class属性之后，且没有其他属性和标签时。

	- 采用的就是构造函数创建bean对象，此时如果没有类中没有默认构造函数，则对象无法创建。

	- ```xml
		<bean id="accountService"class="service.impl.AccountServiceImpl"/>
		```

 2.  使用普通工厂中的方式创建对象（使用某个类中的方法创建对象）

	- ```xml
		<bean id="instanceFactory" class="factory.InstanceFactory"/>
		<bean id="accountService" factory-bean="instanceFactory" factory-method="getAccountService"/>
		```

	- ```java
		public class InstanceFactory {
		    public AccountService getAccountService() {
		        return new AccountServiceImpl();
		    }
		}
		```

 3.  使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象)

	- ```xml
		<bean id="accountService" class="factory.StaticFactory" factory-method="getAccountService"/>
		```

	- ```java
		public class StaticFactory {
		    public static AccountService getAccountService() {
		        return new AccountServiceImpl();
		    }
		}
		```

#### bean的作用范围调整：

- bean标签的scope属性：
	- 作用：用于指定bean的作用范围。
	- 取值：
		- singleton：单例（默认值）
		- prototype：多例
		- request：作用于web应用的请求范围
		- session：作用于web应用的会话范围
		- global-session：作用于集群环境的会话范围，当不是集群环境时，就是session

#### bean对象的生命周期：

- 单例对象：单例对象的生命周期和容器相同

	- 出生：当容器创建时，对象出生
	- 或者：当容器还在，对象一直活着
	- 死亡：容器销毁，对象消亡

- 多例对象：

	- 出生：当使用对象时，spring框架为我们创建
	- 活着：对象只要在使用过程中就一直活着。
	- 死亡：当对象长时间不用，且没有其他对象引用时，由Java的垃圾回收器回收

- ```xml
	<bean id="accountService" class="service.impl.AccountServiceImpl"
	          scope="prototype" init-method="init" destroy-method="destory"/>
	```

#### 获取spring的`Ioc`核心容器，并根据id获取对象

- `ApplicationContext`的三个常用实现类：

	- `ClassPathXmlApplicationContext`：加载类路径下的配置文件，要求配置文件必须在类路径下
	- `FileSystemXmlApplicationContext`：加载磁盘任意位置的配置文件
	- `AnnotationConfigApplicationContext`：读取注解创建容器

- 核心容器两个接口引发的问题：

	- `ApplicationContext`：单例对象适用
		- 它在构建核心容器时，创建对象采取的策略是采用`立即加载`的方式。只要一读取完配置文件，马上就创建配置文件配置的对象。
	- `BeanFactory`：多例对象适用
		- 它在构建核心容器时，创建对象采取的策略是采用`延迟加载`的方式。什么时候根据id获取对象，什么时候才真正的创建对象。

- `bean.xml`：

- ```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	        https://www.springframework.org/schema/beans/spring-beans.xsd">
	
	    <bean id="accountDao" class="dao.impl.AccountDaoImpl"/>
	
	    <bean id="accountService" class="service.impl.AccountServiceImpl"
	          scope="prototype" init-method="init" destroy-method="destory"/>
	</beans>
	```

	

- ```java
	public class Client {
	    public static void main(String[] args) {
	        // ApplicationContext 立即加载
	        //1.获取核心容器对象
	        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
	        //2.根据id获取Bean对象
	        AccountService service = (AccountService) ac.getBean("accountService");
	        AccountService service1 = ac.getBean("accountService", AccountService.class);
	        service.saveAccount();
	        System.out.println(service);
	        System.out.println(service1);
	
	        // BeanFactory  延迟加载
	        /*Resource resource = new ClassPathResource("bean.xml");
	        BeanFactory beanFactory = new XmlBeanFactory(resource);
	        AccountService service = (AccountService) beanFactory.getBean("accountService");
	        System.out.println(service);*/
	
	    }
	}
	```

### spring的依赖注入DI（Dependency Injection）

- 依赖关系的维护称为依赖注入

- 能注入的数据：三类

	- 基本类型和String
	- 其他bean类型（在配置文件中或注解配置过的bean）
	- 复杂类型/集合类型

- 注入的方式：三种：

	- 使用构造函数提供
	- 使用set方法提供
	- 使用注解提供

- 构造函数注入：

	- 使用的标签：`<constructor-arg />`

	- 标签出现的位置：bean标签

	- 标签中的属性：

		- type：用于指定要注入的数据类型，该数据类型也是构造函数中某个或某些参数的类型
		-  index：用于指定要注入的数据给构造函数中指定索引位置的参数赋值。索引的位置从0开始。
		-  name：用于指定给构造函数中指定名称的参数赋值
		- --------------以上三个用于指定给构造器中哪个参数赋值-----------------------
		- value：用于提供基本类型和String类型的数据
		- ref：用于指定那个其他的bean类型数据。指spring的`Ioc`核心容器中出现过的bean对象

	- 优势：在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功

	- 弊端：改变了bean对象的实例化方式，是我们在创建对象时，如果用不到这些数据，也必须提供

	- ```xml
		<bean id="accountService" 		class="service.impl.AccountServiceImpl">
		        <constructor-arg name="name" value="张三"/>
		        <constructor-arg name="age" value="20"/>
		        <constructor-arg name="birthday" ref="now"/>
		</bean>
		<!-- 配置一个日期对象 -->
		<bean id="now" class="java.util.Date"/>
		```

- set方法注入 更常用

	- 涉及的标签：`<property />`

	- 出现的位置：bean标签

	- 标签中的属性：

		- name：用于指定注入时所调用的set方法名称
		- value：用于提供基本类型和String类型的数据
		- ref：用于指定那个其他的bean类型数据。指spring的`Ioc`核心容器中出现过的bean对象

	- 优势：创建对象时没有明确的限制，可以直接使用默认构造函数

	- 弊端：如果有某个成员必须有值，则可能忘记赋值

	- ```xml
		<bean id="accountService2" class="service.impl.AccountServiceImpl2">
		        <property name="name" value="张三"/>
		        <property name="age" value="21"/>
		</bean>
		```

- 复杂类型/集合类型的注入：

	- 用于给list结构集合注入的标签：

		- list
		- array
		- set

	- 用于给map结构集合注入的标签：

		- map
		- props

	- ```xml
		<bean id="accountService3" class="service.impl.AccountServiceImpl3">
		        <property name="myStrs">
		            <array value-type="java.lang.String">
		                <value>AAA</value>
		                <value>BBB</value>
		                <value>CCC</value>
		            </array>
		        </property>
		        <property name="list">
		            <list value-type="java.lang.String">
		                <value>AAA</value>
		                <value>BBB</value>
		                <value>CCC</value>
		            </list>
		        </property>
		        <property name="mySet">
		            <set value-type="java.lang.String">
		                <value>AAA</value>
		                <value>BBB</value>
		                <value>CCC</value>
		            </set>
		        </property>
		        <property name="myMap">
		            <map>
		                <entry key="key1" value="AAA"/>
		                <entry key="key2" value="BBB"/>
		                <entry key="key3" value="CCC"/>
		            </map>
		        </property>
		        <property name="myProps">
		            <props>
		                <prop key="key1">AAA</prop>
		                <prop key="key2">BBB</prop>
		                <prop key="key3">CCC</prop>
		            </props>
		        </property>
		</bean>
		```

