package factory;

import service.AccountService;
import service.impl.AccountServiceImpl;

public class StaticFactory {
    public static AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
