package com.term.moviesite.web.interceptor.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InterceptorExceptionEntity {
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

    public InterceptorExceptionEntity(HttpStatus status, String errorCode, String errorMessage){
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public InterceptorExceptionEntity(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
