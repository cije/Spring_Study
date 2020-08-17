package com.ce.test;

import com.ce.domain.Account;
import com.ce.service.AccountService;
import config.SpringConfiguration;
// import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用junit单元测试：测试配置
 * spring整合junit的配置：
 * 1.导入spring整合junit的包 spring-test
 * 2.使用Junit提供的一个注解把原有的main方法替换成spring提供的
 * 3.告知spring运行期，spring和Ioc创建是基于xml还是注解的，并且告知xml位置
 * ContextConfiguration
 * locations:指定xml文件的位置，加上classpath关键字，表示类路径下
 * classes：注解类位置
 * <p>
 * 当我们使用spring 5.x版本时，必须junit版本4.12及以上
 */
// @RunWith(SpringJUnit4ClassRunner.class)  //junit4
@ExtendWith(SpringExtension.class)  //junit5
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountSerrviceTest {

    @Autowired
    private AccountService service;

    @Test
    public void testFindAll() {
        List<Account> accounts = service.findAllAccount();
        accounts.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        Account account = service.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("张三");
        account.setMoney(1122.0f);
        service.saveAccount(account);
        testFindAll();
    }

    @Test
    public void testUpdate() {
        Account account = service.findAccountById(4);
        account.setMoney(12580.0f);
        service.updateAccount(account);
        testFindAll();
    }

    @Test
    public void testDel() {
        service.deleteAccount(5);
    }
}
