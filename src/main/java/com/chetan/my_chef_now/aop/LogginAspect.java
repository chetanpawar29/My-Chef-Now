package com.chetan.my_chef_now.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogginAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogginAspect.class);

    // execution(return-type(*) fully-collified-classname(com.chetan.my_chef_now.service.*).method-name(*)(args(..)))  --> * repre all class or all methods or .. repre all args

    @Before("execution(* com.chetan.my_chef_now.service.*.*(..))")
    public void logMethodCall(JoinPoint jp)
    {
        LOGGER.info("Method called -> "+jp.getSignature().getName());
    }

    @After("execution(* com.chetan.my_chef_now.service.*.*(..))")
    public void methodExecuted(JoinPoint jp)
    {
        LOGGER.info("Method Executed.."+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.chetan.my_chef_now.service.*.*(..))")
    public void methodIssues(JoinPoint jp)
    {
        LOGGER.info("Method has some issues.."+jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.chetan.my_chef_now.service.*.*(..))")
    public void methodExecutedSuccessfully(JoinPoint jp)
    {
        LOGGER.info("Method Executed Successfully.."+jp.getSignature().getName());
    }
}
