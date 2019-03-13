package com.srijan.springfundamentals.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * For Spring to recognize a filter, define it as a bean with @Component
 *
 */
@Slf4j
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        log.info("Logging Request {} : {} "
                , req.getMethod()
                , req.getRequestURI()
        );

        chain.doFilter(request , response);

        log.info(" Logging Response : {}  ",
                resp.getContentType()
        );
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
