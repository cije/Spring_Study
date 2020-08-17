package com.ce.test;

import com.ce.domain.Account;
import com.ce.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
    public void testTranfer() {
        service.transfer("aaa", "bbb", 100f);
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = service.findAllAccount();
        accounts.forEach(System.out::println);
    }
}
