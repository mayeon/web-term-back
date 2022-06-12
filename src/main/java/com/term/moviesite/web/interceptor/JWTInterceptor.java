package com.term.moviesite.web.interceptor;

import com.term.moviesite.token.JWT;
import com.term.moviesite.web.interceptor.exception.ForbiddenException;
import com.term.moviesite.web.interceptor.exception.UnauthorizedException;
import com.term.moviesite.web.interceptor.exception.InterceptorExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    private JWT jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwtToken = request.getHeader("Authorization");
        String uri = request.getRequestURI();
        System.out.println(jwtToken);

        System.out.println("헤더");
        request.getHeaderNames().asIterator().forEachRemaining(
                headerName -> System.out.println(headerName + ": " + request.getHeader(headerName))
        );

        if (!String.valueOf(request.getMethod()).equals("OPTIONS")) {
            if (jwtToken == null) {
                if (uri.contains("/auth/login")) { // 토큰 발급
                    return true;
                } else {
                    throw new UnauthorizedException(InterceptorExceptionEnum.UNAUTHORIZED);
                }
            } else {
                if (jwt.tokenValidationCheck(jwtToken)) { // 토큰 유효성 검증
                    return true;
                } else {
                    throw new ForbiddenException(InterceptorExceptionEnum.FORBIDDEN);
                }
            }
        } else {
            return true;
        }
    }
}
