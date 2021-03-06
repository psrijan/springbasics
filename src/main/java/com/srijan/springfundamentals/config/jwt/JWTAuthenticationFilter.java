package com.srijan.springfundamentals.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srijan.springfundamentals.entity.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(applicationUser.getUsername() ,
                    applicationUser.getPassword()));
        } catch (IOException ex ) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationDate = ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME ,
                ChronoUnit.MICROS);
        String token = Jwts.builder().setSubject(((User)authResult.getPrincipal()).getUsername())
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
                .compact();
        response.getWriter().write(token);
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }
}
