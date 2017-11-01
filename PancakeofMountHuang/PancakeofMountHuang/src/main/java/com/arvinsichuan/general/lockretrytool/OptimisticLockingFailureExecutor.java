package com.arvinsichuan.general.lockretrytool;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.dao.OptimisticLockingFailureException;

/*
    Author:Administrator
    Time:2017/11/1 9:42
*/
@Aspect
@Configuration
public class OptimisticLockingFailureExecutor implements Ordered {
    private static final int DEFAULT_MAX_RETRIES = 2;

    private int maxRetries = DEFAULT_MAX_RETRIES;
    private int order = 1;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return this.order;
    }

    @Pointcut("@annotation(RetryOnOptimisticLockingFailure)")
    public void retryOnOptFailure() {
        // pointcut mark
    }

    @Around("retryOnOptFailure()")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (OptimisticLockingFailureException ex) {
                if (numAttempts > maxRetries) {
                    //log failure information, and throw exception
                    throw ex;
                } else {
                    //log failure information for audit/reference
                    //will try recovery
                }
            }
        } while (numAttempts <= this.maxRetries);

        return null;
    }
}
