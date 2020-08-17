package service.impl;

import dao.AccountDao;
import factory.BeanFactory;
import service.AccountService;

public class AccountServiceImpl implements AccountService {

    // private AccountDao dao = new AccountDaoImpl();

    private AccountDao dao = (AccountDao) BeanFactory.getBean("accountDao");

    /**
     * 保存帐户
     */
    @Override
    public void saveAccount() {
        dao.saveAccount();
    }
}
