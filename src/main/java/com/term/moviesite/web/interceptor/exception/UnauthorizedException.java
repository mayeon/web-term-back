package com.term.moviesite.web.interceptor.exception;

public class UnauthorizedException extends RuntimeException {
    private InterceptorExceptionEnum error;

    public UnauthorizedException(InterceptorExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
