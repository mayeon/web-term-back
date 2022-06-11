package com.term.moviesite.web.interceptor.exception;

public class ForbiddenException extends RuntimeException {
    private InterceptorExceptionEnum error;

    public ForbiddenException(InterceptorExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
