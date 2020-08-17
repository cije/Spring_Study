package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * 配置类，作用和bean.xml一样
 * spring中的新注解：
 * <p>
 * Configuration
 * 作用：指定当前类是一个配置类
 * 细节：当配置类作为AnnotationConfigApplicationContext创建的参数时，可以不写
 * <p>
 * ComponentScan
 * 作用：用于通过注解指定spring在创建容器扫描的包
 * 属性：
 * value：它和basePackages作用是一样的，用于指定创建容器时要扫描的包。
 * 使用此注解就等同于<context:component-scan base-package="com.ce"/>
 * <p>
 * Bean:
 * 作用：用于把当前方法的返回值作为bean对象存入spring的Ioc容器中
 * 属性：
 * name：用于指定bean的id，默认值是当前方法的名称
 * 细节：当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象，查找方式和Autowired注解的一样
 * <p>
 * Import
 * 作用：导入其他的配置类
 * 属性：
 * value：用于指定其他配置类的字节码
 * 当我们使用Import注解后，导入的是子配置类
 *
 * PropertySource
 * 作用：用于指定properties文件的位置
 * 属性：
 * value：指定文件的名称和路径
 * 关键字 classpath: 表示类路径下
 */
@Configuration
@ComponentScan("com.ce")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
