package com.term.moviesite.web.interceptor.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum InterceptorExceptionEnum {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "403", "권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    InterceptorExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
