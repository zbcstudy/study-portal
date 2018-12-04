package com.wondertek.baiying.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by wd on 2018/6/7.
 */
@Aspect
public class SpringAopTest {

    @Pointcut("execution(* *.test(..)) ")
    public void aopTest() {

    }

    @Before("aopTest()")
    public void beforeTest() {
        System.out.println("before aop");
    }

    @After("aopTest()")
    public void afterTest() {
        System.out.println("after aop");
    }


    @Around("aopTest()")
    public Object aroundTest(ProceedingJoinPoint joinPoint) {

        System.out.println("around before");
        Object o = null;
        try {
            o = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after");
        System.out.println(o);
        return o;
    }
}
