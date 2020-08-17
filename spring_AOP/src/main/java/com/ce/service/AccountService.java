package com.ce.service;

/**
 * 账户的业务层接口
 */
public interface AccountService {
    /**
     * 模拟保存帐户
     */
    void saveAccount();

    /**
     * 模拟更新帐户
     */
    void updateAccount(Integer i);

    /**
     * 删除账户
     * @return
     */
    int deleteAccount();
}
