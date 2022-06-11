package com.term.moviesite.token;

import com.term.moviesite.domain.Users;
import com.term.moviesite.dto.AccountDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.boot.model.source.internal.hbm.ManyToOnePropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWT {
    private String secretKey = "JWTSecretKey"; // 서명에 사용할 secretKey
    private long exp = 1000L * 60 * 60 * 3; // 토큰 사용가능 시간, 3시간
    public final static String AUTHORIZATION_HEADER = "Authorization";

    // 토큰 생성하는 메서드
    public String createToken(AccountDto user) { // 토큰에 담고싶은 값 파라미터로 가져오기
        return Jwts.builder()
                .setHeaderParam("typ", "JWT") // 토큰 타입
                .setSubject("userToken") // 토큰 제목
                .setExpiration(new Date(System.currentTimeMillis() + exp)) // 토큰 유효시간
                .setClaims(createClaim(user)) // 토큰에 담을 데이터
//                .claim("user", user)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // secretKey를 사용하여 해싱 암호화 알고리즘 처리
                .compact(); // 직렬화, 문자열로 변경
    }

    private Map<String, Object> createClaim(AccountDto acc) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", acc.getUserId());
        map.put("pw", acc.getPassword());
        map.put("isAdmin", acc.getIsAdmin());
        return map;
    }

    public Claims parseJwtToken(String token) {
        tokenValidationCheck(token);

        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private void tokenValidationCheck(String header) {
        if (header == null) {
            throw new IllegalArgumentException();
        }
    }
}
