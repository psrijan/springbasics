package com.srijan.springfundamentals.filter;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
//@Component
@Slf4j
//@Order(1)
public class LoggingFilter extends OncePerRequestFilter {

    public static final String UTF_8 = "utf-8";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Request Logging Filter : doFilterInternal");
        ContentCachingRequestWrapper requestWrapper = wrappedRequest(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = wrappedResponse(httpServletResponse);
        String contentType = requestWrapper.getContentType();
        byte[] content = requestWrapper.getContentAsByteArray();
        filterChain.doFilter(httpServletRequest , httpServletResponse);
        InputStream inputStream = requestWrapper.getInputStream();

        String prefix = "<|";
        val queryString = httpServletRequest.getQueryString();
        String requestBody = IOUtils.toString(requestWrapper.getInputStream(), UTF_8);

        if (requestBody == null) {
            log.debug("{} {} {}", prefix, httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        } else {
            log.debug("{} {} {} {}", prefix, httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), requestBody);
        }
    }

    private ContentCachingRequestWrapper wrappedRequest(HttpServletRequest request) {
        return new ContentCachingRequestWrapper(request);
    }

    private ContentCachingResponseWrapper wrappedResponse(HttpServletResponse response) {
        return new ContentCachingResponseWrapper(response);
    }
}
