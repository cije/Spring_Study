package com.ce.service.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import com.ce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) //只读型事务的配置
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

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
     * 转账
     *
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) //只读型事务的配置
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
        int i = 1 / 0;
        //6.更新转入账户
        accountDao.updateAccount(target);

    }
}
