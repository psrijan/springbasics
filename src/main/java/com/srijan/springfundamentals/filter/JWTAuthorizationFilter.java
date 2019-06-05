package com.srijan.springfundamentals.filter;

import com.srijan.springfundamentals.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import static com.srijan.springfundamentals.constants.SecurityConstants.SECURITY_HEADER_STRING;
import static com.srijan.springfundamentals.constants.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @NotNull JwtTokenProvider jwtTokenProvider;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager , JwtTokenProvider provider) {
        super(authenticationManager);
        this.jwtTokenProvider=provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SECURITY_HEADER_STRING);

        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request ,response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String bearerToken = request.getHeader(SECURITY_HEADER_STRING);
        if (bearerToken != null && bearerToken.contains(TOKEN_PREFIX)) {
            // parse the token.
            String token = bearerToken.substring(7);
            String user =jwtTokenProvider.getUsername(token);

            boolean isValidToken = jwtTokenProvider.validateToken(token);

            if (isValidToken && user != null) {
                return jwtTokenProvider.getAuthentication(token);
            }
            return null;
        }
        return null;
    }


}
