package com.ce;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import com.ce.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * dao继承JdbcSupport
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService service;

    @Test
    public void test() {
        Account account = service.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testTransfer() {
        service.transfer("aaa", "bbb", 100f);
    }
}
