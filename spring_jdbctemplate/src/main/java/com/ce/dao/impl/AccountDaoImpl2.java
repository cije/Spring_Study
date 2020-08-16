package com.ce.dao.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * 账户持久层实现类
 */
public class AccountDaoImpl2 extends JdbcDaoSupport implements AccountDao {

    /**
     * 根据id查询帐户
     *
     * @param id id
     * @return 帐户
     */
    @Override
    public Account findAccountById(Integer id) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where  id=?", new BeanPropertyRowMapper<Account>(Account.class), id);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    /**
     * 根据名称查询帐户
     *
     * @param name name
     * @return 帐户
     */
    @Override
    public Account findAccountByName(String name) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where  name=?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if (accounts.isEmpty()) {
            return null;
        } else if (accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        } else {
            return accounts.get(0);
        }
    }

    /**
     * 更新帐户
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name=?,money=? where  id=?", account.getName(), account.getMoney(), account.getId());
    }
}
