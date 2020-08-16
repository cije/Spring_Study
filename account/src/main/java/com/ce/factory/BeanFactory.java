package com.ce.factory;

import com.ce.domain.Account;
import com.ce.service.AccountService;
import com.ce.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    private AccountService accountService;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public AccountService getAccountService() {
        AccountService proxyService = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 添加事务的支持
             * @param proxy
             * @param method
             * @param args
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object rtValue = null;
                try {
                    //开启事务
                    transactionManager.beginTransaction();
                    //执行操作
                    rtValue = method.invoke(accountService, args);
                    //提交事务
                    transactionManager.commit();
                    //返回结果
                    return rtValue;
                } catch (Exception e) {
                    //回滚操作
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    //释放连接
                    transactionManager.release();
                }
            }
        });
        return proxyService;
    }
}
