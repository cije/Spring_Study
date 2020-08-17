package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import org.springframework.format.annotation.DateTimeFormat;
import service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService {

    private AccountDao dao = new AccountDaoImpl();

    // 如果是经常变化的数据，并不适用于注入的方式

    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    /**
     * 保存帐户
     */
    @Override
    public void saveAccount() {
        dao.saveAccount();
        System.out.println(name + " " + age + " " + birthday);
    }
}
