package dao.impl;

import dao.AccountDao;

public class AccountDaoImpl implements AccountDao {

    /**
     * 保存账户
     */
    @Override
    public void saveAccount() {
        System.out.println("保存了帐户");
    }
}
