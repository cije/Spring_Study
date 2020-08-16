package com.ce.test;

import com.ce.domain.Account;
import com.ce.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用junit单元测试：测试配置
 */
public class AccountSerrviceTest {
    ClassPathXmlApplicationContext ac = null;
    AccountService service = null;

    @BeforeEach
    void init() {
        //获取容器
        ac = new ClassPathXmlApplicationContext("bean.xml");
        service = ac.getBean("accountService", AccountService.class);
    }

    @AfterEach
    void destroy() {
        if (ac != null) {
            ac.close();
        }
    }

    @Test
    void testFindAll() {
        List<Account> accounts = service.findAllAccount();
        accounts.forEach(System.out::println);
    }

    @Test
    void testFindOne() {
        Account account = service.findAccountById(1);
        System.out.println(account);
    }

    @Test
    void testSave() {
        Account account = new Account();
        account.setName("张三");
        account.setMoney(1122.0f);
        service.saveAccount(account);
        testFindAll();
    }

    @Test
    void testUpdate() {
        Account account=service.findAccountById(4);
        account.setMoney(12580.0f);
        service.updateAccount(account);
        testFindAll();
    }

    @Test
    void testDel() {
        service.deleteAccount(5);
    }
}
