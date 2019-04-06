package com.srijan.springfundamentals.config;

import com.srijan.springfundamentals.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("**/**").hasRole("USER")
                .antMatchers(HttpMethod.GET , "**/**").permitAll()
                .antMatchers(HttpMethod.POST, "**/**").permitAll()
                .antMatchers(HttpMethod.PUT, "**/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "**/**").permitAll()
                .antMatchers()
                .authenticated();

        httpSecurity.addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);
    }

}
