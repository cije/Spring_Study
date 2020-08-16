package com.ce.test;

import com.ce.domain.Account;
import com.ce.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * 使用junit单元测试：测试配置
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountSerrviceTest {
    @Autowired
    AccountService service;

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
        Account account = service.findAccountById(4);
        account.setMoney(12580.0f);
        service.updateAccount(account);
        testFindAll();
    }

    @Test
    void testDel() {
        service.deleteAccount(5);
    }
}
