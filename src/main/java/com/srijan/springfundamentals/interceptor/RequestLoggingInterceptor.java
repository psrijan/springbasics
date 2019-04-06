package com.srijan.springfundamentals.interceptor;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Slf4j
@Component
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

    public static final String UTF_8 = "utf-8";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("Request Logging Interceptor : preHandle");
//        ContentCachingRequestWrapper requestWrapper = wrappedRequest(request);
//        ContentCachingResponseWrapper responseWrapper = wrappedResponse(response);
//        String contentType = requestWrapper.getContentType();
//        byte[] content = requestWrapper.getContentAsByteArray();
//
//        InputStream inputStream = requestWrapper.getInputStream();
//
//        String prefix = "<|";
//        val queryString = requestWrapper.getQueryString();
//        String requestBody = ""; //IOUtils.toString(req.getInputStream(), UTF_8);
//
//        if (requestBody == null) {
//            log.debug("{} {} {}", prefix, requestWrapper.getMethod(), requestWrapper.getRequestURI());
//        } else {
//            log.debug("{} {} {} {}", prefix, requestWrapper.getMethod(), requestWrapper.getRequestURI(), requestBody);
//        }
        return super.preHandle(request, response, handler);
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.debug("Logging Interceptor : afterCompletion");
//        ContentCachingRequestWrapper requestWrapper = wrappedRequest(request);
//        ContentCachingResponseWrapper responseWrapper = wrappedResponse(response);
//        int statusCode = responseWrapper.getStatusCode();
//        String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
//        if(responseBody == null) {
//            log.debug("{} " , responseWrapper.getContentType() );
//        } else {
//            log.debug("{} {} {}" , responseWrapper.getContentType()  , responseBody , statusCode);
//        }
//        responseWrapper.copyBodyToResponse();
//        super.afterCompletion(request, response, handler, ex);
//    }

    private ContentCachingRequestWrapper wrappedRequest(HttpServletRequest request) {
        return new ContentCachingRequestWrapper(request);
    }

    private ContentCachingResponseWrapper wrappedResponse(HttpServletResponse response) {
        return new ContentCachingResponseWrapper(response);
    }
}
