package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import service.AccountService;

import java.util.*;

public class AccountServiceImpl3 implements AccountService {

    private AccountDao dao = new AccountDaoImpl();

    private String[] myStrs;
    private List<String> list;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myProps;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    /**
     * 保存帐户
     */
    @Override
    public void saveAccount() {
        dao.saveAccount();
        System.out.println(Arrays.toString(myStrs));
        System.out.println(list);
        System.out.println(myMap);
        System.out.println(mySet);
        System.out.println(myProps);
    }
}
