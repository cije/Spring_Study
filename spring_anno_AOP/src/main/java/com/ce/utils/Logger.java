package com.ce.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger")
@Aspect  //表示当前类是一个切面类
public class Logger {
    @Pointcut("bean(accountService)")
    private void pt1() {

    }

    /**
     * 前置通知
     */
    // @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("前置通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 后置通知
     */
    // @AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 异常通知
     */
    // @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 最终通知
     */
    // @After("pt1()")
    public void afterPintLog() {
        System.out.println("最终通知 Logger类的printLog方法开始记录日志了...");
    }

    /**
     * 环绕通知
     * 问题：
     * 当我们配置了环绕通知之后，切入点方法没有执行，而环绕通知方法执行
     * 分析：
     * 通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的业务层代码调用，而我们的代码没有
     * 解决：
     * spring框架为我们提供了一个接口：ProceedingJoinPoint 该接口有一个方法proceed，此方法明确调用切入点方法
     * 该接口可以作为环绕通知的方法参数，在程序执行时，spring会为我们提供该接口的实现类供我们使用。
     * <p>
     * spring中的环绕通知：
     * 它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式
     */
    @Around("pt1()")
    public Object arroundPintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            System.out.println("Logger类的printLog方法开始记录日志了... 前置");
            //得到方法执行所需的参数
            Object[] args = pjp.getArgs();
            // 明确调用业务层方法（切入点方法）
            rtValue = pjp.proceed();

            System.out.println("Logger类的printLog方法开始记录日志了... 后置");

            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("Logger类的printLog方法开始记录日志了... 异常");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("Logger类的printLog方法开始记录日志了... 最终");
        }
    }
}
