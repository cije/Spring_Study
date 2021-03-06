package com.ce.dao.impl;

import com.ce.dao.AccountDao;
import com.ce.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据id查询帐户
     *
     * @param id id
     * @return 帐户
     */
    @Override
    public Account findAccountById(Integer id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where  id=?", new BeanPropertyRowMapper<Account>(Account.class), id);
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
        List<Account> accounts = jdbcTemplate.query("select * from account where  name=?", new BeanPropertyRowMapper<Account>(Account.class), name);
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
        jdbcTemplate.update("update account set name=?,money=? where  id=?", account.getName(), account.getMoney(), account.getId());
    }
}
