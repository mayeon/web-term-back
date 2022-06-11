package com.term.moviesite.web;

import com.term.moviesite.domain.Users;
import com.term.moviesite.dto.AccountDto;
import com.term.moviesite.service.UserService;
import com.term.moviesite.token.JWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final UserService userService;
    private final JWT jwt;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AccountDto acc, HttpServletResponse response) {
        Users user = userService.findUserById(acc.getUserId());

        if(acc.getUserId().equals(user.getUserId()) && acc.getPassword().equals(user.getPassword())) {
            acc.setIsAdmin(user.getIsAdmin());
            String token = jwt.createToken(acc); // 사용자 정보로 토큰 생성
            response.setHeader(JWT.AUTHORIZATION_HEADER, "Bearer " + token); // client에 token 전달
            return new ResponseEntity<Object>("login Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("login Fail", HttpStatus.OK);
        }
    }
}
