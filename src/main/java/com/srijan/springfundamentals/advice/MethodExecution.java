package com.srijan.springfundamentals.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MethodExecution {

    @Around("@annotation(com.srijan.springfundamentals.advice.qualifier.LogTimeExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result= joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.debug("Execution Time for {}  is {}" , joinPoint.getSignature() ,executionTime);
        return  result;
    }
}
