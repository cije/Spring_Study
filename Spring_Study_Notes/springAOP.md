# Spring AOP

## 什么是AOP

>​		全称: 面向切面编程(Aspect Oriented Programming), 通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。

## 学习AOP意义

> ​		利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。可以在不修改源码的情况下对程序进行增强。

## AOP应用场景

- 权限校验
- 日志记录
- 性能监控
- 事务控制

## Spring AOP底层实现原理

- JDK 的动态代理：针对实现了接口的类产生代理。

- `Cglib`的动态代理：针对没有实现接口的类产生代理，应用的是底层的字节码增强技术，生成当前类的子类对象。

	注意:
	1、如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
	2、如果目标对象实现了接口，可以强制使用`CGLIB`实现AOP
	3、如果目标对象没有实现了接口，必须采用`CGLIB`库，spring会自动在JDK动态代理和`CGLIB`之间转换

## Spring AOP 相关术语

- **Joinpoint**: 连接点，可以被拦截到的点。(可以被增强的方法，称为连接点)

- **Pointcut**: 切入点，真正被拦截到的点。在实际开发中，只对某个方法进行增强，这个方法就是切入点。

- **Advice**: 通知、增强(方法层面的增强)。现在对某个方法进行权限校验，权限校验的方法称为是通知。

- **Introduction**：引介, 类层面的增强，在类里面动态的增加方法和属性。

- Target：被增强的对象。

- Weaving：织入，将通知Advice应用到目标Target的过程。

- Aspect：切面，多个通知和多个切入点的组合。

	![](F:\onedrive\文档\spring笔记\AOP术语.png)

- 通知的分类

	![](F:\onedrive\文档\spring笔记\通知的类型.jpg)

## Spring AOP开发

- ### 基于XML的AOP配置步骤

1. 把通知Bean也交给spring来管理
2. 使用`aop:config`标签表明开始AOP的配置
3. 使用`aop:aspect`标签表明配置切面
	- `id`属性：给切面提供一个唯一标识
	- `ref`属性：指定通知类bean的id
4. 在`aop:aspect`标签内部使用对应的标签来配置通知的类型
	- `aop:before`：前置通知
	- `aop:after-returning`：后置通知
	- `aop:after-throwing`：异常通知
	- `aop:after`：最终通知
	- `aop:arround`：环绕通知
		- `method`属性：用于指定切面类中哪个方法是该类型通知
		- `pointcut`属性：用于指定切入点表达式，该表达式的含义指的是对业务层中哪些方法增强
			- 切入点表达式：
				- `execution(表达式)`
				- 表达式：`访问修饰符 返回值 包名.包名..类名.方法名(参数列表)`
				- 例：`execution(public void com.ce.service.impl.AccountServiceImpl.saveAccount())`
				- 访问修饰符可以省略
				- 返回值可以使用通配符，表示任意返回值
				- 包名可以使用通配符，但是有几级包，就需要写几个*，包名可以使用..表示当前包及其子包
				- 类名和方法名都可以使用通配符
				- 参数列表：
					- 可以直接写数据类型：                   
						- 基本类型直接写名称：  `int`
						- 引用类型写`包名.类名`的方式  `java.lang.String`
					- 可以使用通配符表示任意类型，但是必须有参数
					- 可以使用`..`表示有无参数均可
				- 全通配写法：
					- `execution（* *..*.*(..))`
				- 实际开发中切入点表达式的通常写法：
					- 切到业务实现类下的所有方法
						- `execution(* com,ce.service.impl.*.*(..)）`

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
   
    <!--配置Logger类-->
    <bean id="logger" class="com.ce.utils.Logger"/>

    <!--配置AOP-->
    <aop:config>
        <!--配置切入点表达式 id属性用于指定表达式的唯一标识。expression用于指定表达式内容-->
        <aop:pointcut id="pt1" expression="bean(accountService)"/>
        <!--配置切面-->
        <aop:aspect id="logAdvice" ref="logger">
            <!--配置通知的类型，并且建立通知方法和切入带你方法的关联-->
            <!--配置环绕通知-->
            <aop:around method="arroundPintLog" pointcut-ref="pt1"/>

            <!--前置通知：在切入点方法执行之前执行
            <aop:before method="beforePrintLog" pointcut-ref="pt1"/>-->
            <!--后置通知：在切入点方法正常执行之后执行，它和异常通知只能执行一个
            <aop:after-returning method="afterReturningPrintLog" pointcut-ref="pt1"/>-->
            <!--异常通知：在切入点方法执行产生异常之后执行，它和后置通知只能执行一个
            <aop:after-throwing method="afterThrowingPrintLog" pointcut-ref="pt1"/>-->
            <!--最终通知，无论切入点方法是否正常执行它都会在其后面执行。
            <aop:after method="afterPintLog" pointcut-ref="pt1"/>-->
        </aop:aspect>
    </aop:config>
</beans>
```

```java
public class Logger {
    /**
     * 前置通知
     */
    public void beforePrintLog() {
        System.out.println("前置通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 后置通知
     */
    public void afterReturningPrintLog() {
        System.out.println("后置通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 最终通知
     */
    public void afterPintLog() {
        System.out.println("最终通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 环绕通知
     * 问题：
     * 当我们配置了环绕通知之后，切入点方法没有执行，而环绕通知方法执行
     * 分析：
     * 通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的业务层代码调用，而我们的代码没有
     * 解决：
     * spring框架为我们提供了一个接口：ProceedingJoinPoint 该接口有一个方法proceed，此方法明确调用切入点方法
     * 该接口可以作为环绕通知的方法参数，在程序执行时，spring会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     * 它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式
     */
    public Object arroundPintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            System.out.println("Logger类的printLog方法开始记录日志了... 前置");
            //得到方法执行所需的参数
            Object[] args = pjp.getArgs();
            // 明确调用业务层方法（切入点方法）
            rtValue = pjp.proceed();

            System.out.println("Logger类的printLog方法开始记录日志了... 后置");

            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("Logger类的printLog方法开始记录日志了... 异常");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("Logger类的printLog方法开始记录日志了... 最终");
        }
    }
}
```

- ### 基于XML的AOP配置步骤

	```xml
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:aop="http://www.springframework.org/schema/aop"
	  xmlns:context="http://www.springframework.org/schema/context"
	 xsi:schemaLocation="http://www.springframework.org/schema/beans
	 https://www.springframework.org/schema/beans/spring-beans.xsd
	        http://www.springframework.org/schema/aop
	        https://www.springframework.org/schema/aop/spring-aop.xsd
	        http://www.springframework.org/schema/context
	        https://www.springframework.org/schema/context/spring-context.xsd">
	
	    <!--配置spring 创建容器时要扫描的包-->
	    <context:component-scan base-package="com.ce"/>
	
	    <!--配置spring开启注解AOP的支持-->
	    <aop:aspectj-autoproxy/>
	</beans>
	```

	```java
	@Component("logger")
	@Aspect  //表示当前类是一个切面类
	public class Logger {
	    @Pointcut("bean(accountService)")
	    private void pt1() {
	
	    }
	
	    /**
	     * 前置通知
	     */
	    @Before("pt1()")
	    public void beforePrintLog() {
	        System.out.println("前置通知 Logger类的printLog方法开始记录日志了...");
	    }
	
	    /**
	     * 后置通知
	     */
	    @AfterReturning("pt1()")
	    public void afterReturningPrintLog() {
	        System.out.println("后置通知 Logger类的printLog方法开始记录日志了...");
	    }
	
	    /**
	     * 异常通知
	     */
	    @AfterThrowing("pt1()")
	    public void afterThrowingPrintLog() {
	        System.out.println("异常通知 Logger类的printLog方法开始记录日志了...");
	    }
	
	    /**
	     * 最终通知
	     */
	    @After("pt1()")
	    public void afterPintLog() {
	        System.out.println("最终通知 Logger类的printLog方法开始记录日志了...");
	    }
	
	    /**
	     * 环绕通知
	     * 问题：
	     * 当我们配置了环绕通知之后，切入点方法没有执行，而环绕通知方法执行
	     * 分析：
	     * 通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的业务层代码调用，而我们的代码没有
	     * 解决：
	     * spring框架为我们提供了一个接口：ProceedingJoinPoint 该接口有一个方法proceed，此方法明确调用切入点方法
	     * 该接口可以作为环绕通知的方法参数，在程序执行时，spring会为我们提供该接口的实现类供我们使用。
	     * <p>
	     * spring中的环绕通知：
	     * 它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式
	     */
	    @Around("pt1()")
	    public Object arroundPintLog(ProceedingJoinPoint pjp) {
	        Object rtValue = null;
	        try {
	            System.out.println("Logger类的printLog方法开始记录日志了... 前置");
	            //得到方法执行所需的参数
	            Object[] args = pjp.getArgs();
	            // 明确调用业务层方法（切入点方法）
	            rtValue = pjp.proceed();
	
	            System.out.println("Logger类的printLog方法开始记录日志了... 后置");
	
	            return rtValue;
	        } catch (Throwable throwable) {
	            System.out.println("Logger类的printLog方法开始记录日志了... 异常");
	            throw new RuntimeException(throwable);
	        } finally {
	            System.out.println("Logger类的printLog方法开始记录日志了... 最终");
	        }
	    }
	}
	```

	