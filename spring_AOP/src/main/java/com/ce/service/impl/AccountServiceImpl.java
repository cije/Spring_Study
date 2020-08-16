package com.ce.service.impl;

import com.ce.service.AccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    /**
     * 模拟保存帐户
     */
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    /**
     * 模拟更新帐户
     *
     * @param i
     */
    @Override
    public void updateAccount(Integer i) {
        System.out.println("执行了更新");
    }

    /**
     * 删除账户
     *
     * @return
     */
    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
