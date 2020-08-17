package com.ce.service.impl;

import com.ce.dao.AccountDao;
import com.ce.dao.impl.AccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.ce.service.AccountService;

import javax.annotation.Resource;

/**
 * 账户的业务层实现类
 * 曾经的xml配置：
 * <bean id="accountService" class="service.impl.AccountServiceImpl"
 * scope="" init-method="" destory-method="">
 * <property name="" value=""/>
 * </bean>
 * <p>
 * - 用于创建对象的
 * 相当于<bean></bean>标签
 * Component：
 * 作用：用于把当前类对象存入spring容器中
 * 属性：
 * value：用于指定bean的id，默认值是当前的类名首字母小写
 * Controller：表现层
 * Service：业务层
 * Repository：持久层
 * <p>
 * - 用于注入数据的
 * <bean></bean>标签中的<property/>
 * Autowired：
 * 作用：自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 * 如果Ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 * 出现位置：
 * 可以是变量上，也可以是方法上
 * 细节：
 * 在使用注解时，set方法就不是必须的
 * Qualifier：
 * 作用：
 * 在按照类中注入的基础上再按照名称注入。它在给类成员注入时不能单独使用。但是给方法参数注入时可以。
 * 属性：
 * value：用于指定注入bean的id
 * Resource
 * 作用：直接按照bean的id注入，可以单独使用
 * 属性：
 * name：用于指定bean的id
 * 以上三个注入都只能注入其他其他bean类型的数据，无法应用于基本类型和String类型
 * Value：
 * 作用：用于注入基本类型和String类型的数据
 * 属性：
 * value：用于指定数据的值。它可以使用spring中SpEL（spring中的EL表达式）
 * SpEL的写法：${表达式}
 * 集合类型的注入只能通过XML来实现
 * <p>
 * <p>
 * 用于改变作用范围的
 * scope属性
 * Scope
 * 作用：用于指定bean的作用范围
 * 属性：
 * value：指定范围的取值，默认单例
 * 和生命周期相关（了解）
 * init-method  destory-method
 * PreDestroy
 *      作用：用于指定销毁的方法
 * PostConstruct
 *      作用：用于指定初始化方法
 */
@Component(value = "accountService")
public class AccountServiceImpl implements AccountService {

    // @Autowired
    // @Qualifier("accountDao2")

    @Resource(name = "accountDao2")
    private AccountDao dao;

    /**
     * 保存帐户
     */
    @Override
    public void saveAccount() {
        dao.saveAccount();
        System.out.println(dao);
    }
}
