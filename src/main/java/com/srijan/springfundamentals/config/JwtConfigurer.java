package com.srijan.springfundamentals.config;

import com.srijan.springfundamentals.filter.JwtTokenFilter;
import com.srijan.springfundamentals.provider.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain , HttpSecurity> {

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private JwtTokenFilter jwtTokenFilter;
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);
//    }
}