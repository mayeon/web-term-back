package com.term.moviesite.web;

import com.term.moviesite.domain.Movies;
import com.term.moviesite.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    @Autowired
    LikeRepository likeRepository;
    @GetMapping("/asd")
    public void test() {
        likeRepository.addLike(1L, "choi");

    }
}
