package com.srijan.springfundamentals.advice;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class ApiMaxHits {

    @Autowired
    private UserRepository userRepository;

    @Before("@annotation(com.srijan.springfundamentals.advice.qualifier.MaxHitsBlocker)")
    public Object checkApiHits(ProceedingJoinPoint joinPoint) throws Throwable {
        String username ="";
        ApplicationUser applicationUser =  userRepository.findByUsername(username).get();

        if(applicationUser.getApiHits()< 1000) {
            Object result = joinPoint.proceed();
            return result;
        }

        return null;
    }


}
