package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import service.AccountService;

import java.util.Date;

public class AccountServiceImpl2 implements AccountService {

    private AccountDao dao = new AccountDaoImpl();

    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
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
