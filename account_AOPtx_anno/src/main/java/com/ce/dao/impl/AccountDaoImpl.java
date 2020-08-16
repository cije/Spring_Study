package com.ce.dao.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import com.ce.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private QueryRunner runner;
    @Autowired
    private ConnectionUtils connectionUtils;

    /**
     * 查询所有
     */
    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public Account findAccountById(Integer id) {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account where id=?", new BeanHandler<>(Account.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存
     *
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "insert into account(name, money) VALUES (?,?)", account.getName(), account.getMoney());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "update  account set name =?, money=? where id=?", account.getName(), account.getMoney(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteAccount(Integer id) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "delete from  account where id=?", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据名称查询帐户
     *
     * @param accountName 账户名称
     * @return 如果有唯一的一个结果就返回，没有就返回null
     * 如果结果集超过1个就抛异常
     */
    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtils.getThreadConnection(), "select * from account where name=?", new BeanListHandler<>(Account.class), accountName);
            if (accounts.isEmpty()) {
                return null;
            } else if (accounts.size() > 1) {
                throw new RuntimeException("结果集不唯一，数据有问题");
            } else {
                return accounts.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
