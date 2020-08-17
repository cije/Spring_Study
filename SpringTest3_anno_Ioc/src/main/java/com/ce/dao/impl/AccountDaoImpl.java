package com.ce.dao.impl;

import com.ce.dao.AccountDao;
import org.springframework.stereotype.Component;

@Component("accountDao")
public class AccountDaoImpl implements AccountDao {

    /**
     * 保存账户
     */
    @Override
    public void saveAccount() {
        System.out.println("保存了帐户111111");
    }
}
