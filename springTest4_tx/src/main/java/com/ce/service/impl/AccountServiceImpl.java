package com.ce.service.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import com.ce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public Account findAccountById(Integer id) {
        return transactionTemplate.execute(new TransactionCallback<Account>() {
            @Override
            public Account doInTransaction(TransactionStatus transactionStatus) {
                return accountDao.findAccountById(id);
            }
        });
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
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
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
                // int i = 1 / 0;
                //6.更新转入账户
                accountDao.updateAccount(target);
                return null;
            }
        });

    }
}
