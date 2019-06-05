package com.srijan.springfundamentals.config;

import com.srijan.springfundamentals.filter.JWTAuthenticationFilter;
import com.srijan.springfundamentals.filter.JWTAuthorizationFilter;
import com.srijan.springfundamentals.filter.NewLoggingFilter;
import com.srijan.springfundamentals.filter.StubLoggingFilter1;
import com.srijan.springfundamentals.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.srijan.springfundamentals.constants.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//                .antMatchers(WORD_GROUP_URL).hasRole(AuthorityName.USER.toString())
//                .antMatchers(SENTENCE_URL).hasRole(AuthorityName.USER.toString())
//                .antMatchers(WORD_URL).hasRole(AuthorityName.USER.toString())
//                .antMatchers("/word").hasRole(AuthorityName.USER.toString())
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtTokenProvider))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtTokenProvider))
                .addFilterBefore(new StubLoggingFilter1(), JWTAuthenticationFilter.class)
                .addFilterBefore(new NewLoggingFilter(), JWTAuthenticationFilter.class)
                // this disables session creation on Spring Security
                .requestCache().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
