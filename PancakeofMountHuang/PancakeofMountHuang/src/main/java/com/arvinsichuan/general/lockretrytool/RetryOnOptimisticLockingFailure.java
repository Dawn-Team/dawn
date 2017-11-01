package com.arvinsichuan.general.lockretrytool;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
    Author:Administrator
    Time:2017/11/1 9:36
*/
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnOptimisticLockingFailure {
}
