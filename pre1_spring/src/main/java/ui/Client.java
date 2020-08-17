package ui;

import factory.BeanFactory;
import service.AccountService;

/**
 * 模拟一个表现层
 */
public class Client {
    public static void main(String[] args) {
        // AccountService service=new AccountServiceImpl();
        // AccountService service = (AccountService) BeanFactory.getBean("accountService");
        // service.saveAccount();
        for (int i = 0; i < 5; i++) {
            AccountService service = (AccountService) BeanFactory.getBean("accountService");
            System.out.println(service);
            service.saveAccount();
        }
    }
}
