package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

/**
 * 模拟一个表现层
 */
public class Client {
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象
        AccountService service = (AccountService) ac.getBean("accountService");
        service.saveAccount();

    }
}
