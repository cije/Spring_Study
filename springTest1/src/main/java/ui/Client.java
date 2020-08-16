package ui;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import service.AccountService;
import service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层
 */
public class Client {
    /**
     * 获取spring的Ioc核心容器，并根据id获取对象<br/>
     * <p>
     * ApplicationContext的三个常用实现类：
     * <ul>
     * <li>ClassPathXmlApplicationContext:加载类路径下的配置文件，要求配置文件必须在类路径下</li>
     * <li>FileSystemXmlApplicationContext：加载磁盘任意位置的配置文件</li>
     * <li>AnnotationConfigApplicationContext：读取注解创建容器</li>
     * </ul>
     * </p>
     * 核心容器的两个接口引发的问题：
     * ApplicationContext： 单例对象适用
     *  它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。只要一读取完配置文件，马上就创建配置文件配置的对象。
     * BeanFactory：  多例对象适用
     *  它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。什么时候根据id获取对象，什么时候才真正的创建对象。
     */
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
