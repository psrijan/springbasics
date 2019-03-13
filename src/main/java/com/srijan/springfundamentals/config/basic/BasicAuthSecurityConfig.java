package com.srijan.springfundamentals.config.basic;

import com.srijan.springfundamentals.service.CustomerUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//this will ensure that even those urls that have authorization for a particular role defined in config has roles defined in the method annotation @PreAuthorize()
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerUserDetailsService customerUserDetailsService;

    private final AuthenticationEntryPoint entryPoint;

    @Autowired
    public BasicAuthSecurityConfig(CustomerUserDetailsService customerUserDetailsService ,
                                   AuthenticationEntryPoint entryPoint) {
        this.customerUserDetailsService = customerUserDetailsService;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/*/signin/**").hasRole("USER")
                .antMatchers("/*/user/**").hasRole("USER")
                .antMatchers("/*/admin/**").hasRole("ADMIN")
                .antMatchers("/*/user/").hasRole("USER")
                .and()
                .httpBasic()
                .authenticationEntryPoint(entryPoint);
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager(),customerUserDetailsService));
//        .basicAuth();

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                log.info("Corss origin Request");
                registry.addMapping("/**")
                        .allowedMethods("GET" , "POST" , "PUT" , "DELETE")
                        .allowedOrigins("*");
            }
        };
    }

}
