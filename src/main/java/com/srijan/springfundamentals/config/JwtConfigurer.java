package com.srijan.springfundamentals.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Slf4j
//@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain , HttpSecurity> {

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private JWTAuthenticationFilter jwtTokenFilter;
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);
//    }
}
