package com.srijan.springfundamentals.config.jwt;

import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.service.CustomerUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.srijan.springfundamentals.config.jwt.SecurityConstants.HEADER_STRING;
import static com.srijan.springfundamentals.config.jwt.SecurityConstants.TOKEN_PREFIX;
import static com.srijan.springfundamentals.config.jwt.SecurityConstants.SECRET;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final CustomerUserDetailsService customerUserDetailsService;

    public JWTAuthorizationFilter (AuthenticationManager authenticationManager ,
                                   CustomerUserDetailsService customerUserDetailsService) {
        super(authenticationManager);
        this.customerUserDetailsService = customerUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request ,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX , ""))
                .getBody()
                .getSubject();
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        ApplicationUser applicationUser = customerUserDetailsService.loadApplicationUserByUsername(username);
        return username != null? new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities()) : null;
    }



}
