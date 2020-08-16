package com.ce.service.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import com.ce.service.AccountService;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 查询所有
     */
    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    /**
     * 保存
     *
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    /**
     * 更新
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }
}
