package com.ce.service.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import com.ce.service.AccountService;
import com.ce.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 * <p>
 * 事务控制应该都是在业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

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

    /**
     * 转账
     *
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        //1.根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //3.转出账户减钱
        source.setMoney(source.getMoney() - money);
        //4.转入账户加钱
        target.setMoney(target.getMoney() + money);
        //5.更新转出账户
        accountDao.updateAccount(source);
        // int i=1/0;
        //6.更新转入账户
        accountDao.updateAccount(target);

    }
}
