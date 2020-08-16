package factory;

import service.AccountService;
import service.impl.AccountServiceImpl;

public class InstanceFactory {
    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
