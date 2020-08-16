package com.ce.dao;

import com.ce.domain.Account;

/**
 * 帐户的持久层接口
 */
public interface AccountDao {
    /**
     * 根据id查询帐户
     * @param id id
     * @return 帐户
     */
    Account findAccountById(Integer id);

    /**
     * 根据名称查询帐户
     * @param name name
     * @return 帐户
     */
    Account findAccountByName(String name);

    /**
     * 更新帐户
     * @param account
     */
    void updateAccount(Account account);
}
