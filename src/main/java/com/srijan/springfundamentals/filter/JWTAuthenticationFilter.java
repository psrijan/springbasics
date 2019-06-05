package com.srijan.springfundamentals.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srijan.springfundamentals.dto.request.LoginRequest;
import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.srijan.springfundamentals.constants.SecurityConstants.SECURITY_HEADER_STRING;
import static com.srijan.springfundamentals.constants.SecurityConstants.TOKEN_PREFIX;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @NotNull private AuthenticationManager authenticationManager;
    @NotNull private JwtTokenProvider jwtTokenProvider;


    public JWTAuthenticationFilter(@NotNull AuthenticationManager authenticationManager , JwtTokenProvider provider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = provider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("Attemtping Authentication ");
        try {
            LoginRequest loginRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword(),
                            new ArrayList<>()
                    )
            );

        } catch (IOException ex ) {
            throw new RuntimeException("IO Exception in JWT Token Filter");
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info("Successful Authentication");
        ApplicationUser applicationUser = (ApplicationUser)authResult.getPrincipal();
        String token = jwtTokenProvider.createToken(applicationUser.getUsername() , Arrays.asList("ADMIN" , "USER")); //@todo change the hardcoded roles list
        response.setHeader(SECURITY_HEADER_STRING, TOKEN_PREFIX+ token);

    }
}
