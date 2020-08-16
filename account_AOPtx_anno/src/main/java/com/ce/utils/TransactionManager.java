package com.ce.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 事务管理相关的工具类
 * 开启事务 提交事务 回滚事务 释放连接
 */
@Component("transactionManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("bean(accountService)")
    private void pt1() {
    }

    /**
     * 开启事务
     */
    @Before("pt1()")
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    @AfterReturning("pt1()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    @AfterThrowing("pt1()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    @After("pt1()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
