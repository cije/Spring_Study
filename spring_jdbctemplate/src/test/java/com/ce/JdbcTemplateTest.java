package com.ce;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
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
public class JdbcTemplateTest {
    @Autowired
    @Qualifier("accountDao2")
    private AccountDao accountDao;

    @Test
    public void test() {
        Account account = accountDao.findAccountById(1);
        System.out.println(account);
    }
}
