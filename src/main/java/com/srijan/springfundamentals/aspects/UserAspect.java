package com.srijan.springfundamentals.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class UserAspect {
    @Before("execution(* com.srijan.springfundamentals.service.UserService.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Before : {} " , joinPoint.getSignature().getName());
        log.info(" Arguments of {} : {}" , joinPoint.getSignature().getName() , Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.srijan.springfundamentals.service.UserService.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info("After : {} " , joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.srijan.springfundamentals.service.UserService.*(..))" , returning = "result")
    public void after(JoinPoint joinPoint , Object result) {
        log.info("AfterReturning : {} ", joinPoint.getSignature());
        log.info(" Result is : {}" , result);
    }
}
