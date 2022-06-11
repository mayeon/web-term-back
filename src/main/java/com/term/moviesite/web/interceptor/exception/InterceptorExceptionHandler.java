package com.term.moviesite.web.interceptor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InterceptorExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public InterceptorExceptionEntity UnauthorizedExceptionHandler(RuntimeException e) {
        return new InterceptorExceptionEntity(InterceptorExceptionEnum.UNAUTHORIZED.getStatus(), InterceptorExceptionEnum.UNAUTHORIZED.getCode(), InterceptorExceptionEnum.UNAUTHORIZED.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public InterceptorExceptionEntity ForbiddenExceptionHandler(RuntimeException e) {
        return new InterceptorExceptionEntity(InterceptorExceptionEnum.FORBIDDEN.getStatus(), InterceptorExceptionEnum.FORBIDDEN.getCode(), InterceptorExceptionEnum.FORBIDDEN.getMessage());
    }
}
