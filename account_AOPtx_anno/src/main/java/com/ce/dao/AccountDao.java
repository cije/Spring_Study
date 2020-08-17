package com.ce.dao;

import com.ce.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface AccountDao {
    /**
     * 查询所有
     */
    List<Account> findAllAccount();

    /**
     * 根据id查找
     */
    Account findAccountById(Integer id);

    /**
     * 保存
     */
    void saveAccount(Account account);

    /**
     * 更新
     */
    void updateAccount(Account account);

    /**
     * 删除
     */
    void deleteAccount(Integer id);

    /**
     * 根据名称查询帐户
     *
     * @param accountName 账户名称
     * @return 如果有唯一的一个结果就返回，没有就返回null
     * 如果结果集超过1个就抛异常
     */
    Account findAccountByName(String accountName);
}
