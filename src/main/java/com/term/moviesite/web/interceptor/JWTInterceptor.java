package com.term.moviesite.web.interceptor;

import com.term.moviesite.token.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String uri = request.getRequestURI();

        if(jwtToken == null){
            if(uri.contains("/auth/login")) { // 토큰 발급
                return true;
            }
        } else {
            String token = request.getHeader(JWT.AUTHORIZATION_HEADER);
            if(token != null && token.length() > 0) {
                jwt.parseJwtToken(token); // 토큰 유효성 검증
            } else { // 유효한 인증토큰이 아닐 경우
                throw new Exception("유효한 인증토큰이 존재하지 않습니다.");
            }
            return true;
        }
        return false;

//        if(request.getMethod().equals("OPTIONS")) { // preflight로 넘어온 options는 통과
//            return true;
//        } else {
//            if (!request.getRequestURI().contains("/auth/login")) {
//                String token = request.getHeader(JWT.AUTHORIZATION_HEADER);
//                if(token != null && token.length() > 0) {
//                    jwt.parseJwtToken(token); // 토큰 유효성 검증
//                    return true;
//                } else { // 유효한 인증토큰이 아닐 경우
//                    throw new Exception("유효한 인증토큰이 존재하지 않습니다.");
//                }
//            } else {
//                return true;
//            }
//        }
    }
}
