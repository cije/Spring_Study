package com.ce.ui;

import org.springframework.context.ApplicationContext;
import com.ce.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层
 */
public class Client {
    public static void main(String[] args) {
        //1.获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
        AccountService service = (AccountService) ac.getBean("accountService");
        System.out.println(service);
        service.saveAccount();


        ac.close();
    }
}
