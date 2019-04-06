package com.srijan.springfundamentals.advice;

import com.srijan.springfundamentals.dto.response.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler({HttpMessageNotReadableException.class })
    public final ResponseEntity<ApiError> handleException(Exception ex , WebRequest webRequest){

        HttpHeaders httpHeaders = new HttpHeaders();

        if(ex instanceof  HttpMessageNotReadableException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;

            return handleHttpMessageNotReadableException((HttpMessageNotReadableException) ex , httpHeaders, status , webRequest);
        }
        return new ResponseEntity<ApiError>(new ApiError() ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = Arrays.asList(ex.getMessage());
        ApiError apiError = new ApiError();
        apiError.setErrorMessages(errorMessages);
        return handleExceptionInternal(ex, apiError, headers , status, request);
    }

    /** A single place to customize the response body of all Exception types. */
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
