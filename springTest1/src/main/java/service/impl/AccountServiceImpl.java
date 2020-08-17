package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao dao = new AccountDaoImpl();

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    /**
     * 保存帐户
     */
    @Override
    public void saveAccount() {
        dao.saveAccount();
    }

    public void init() {
        System.out.println("对象初始化了");
    }

    public void destory() {
        System.out.println("对象销毁了");
    }
}
