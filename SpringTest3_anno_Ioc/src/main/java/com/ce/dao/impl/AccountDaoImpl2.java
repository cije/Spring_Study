package com.ce.dao.impl;

import com.ce.dao.AccountDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("accountDao2")
@Scope
public class AccountDaoImpl2 implements AccountDao {

    /**
     * 保存账户
     */
    @Override
    public void saveAccount() {
        System.out.println("保存了帐户222222");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化" + this.getClass().getName());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁" + this.getClass().getName());
    }
}
