package com.term.moviesite.repository;

import com.term.moviesite.domain.Likes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LikeRepositoryTest {
    @Autowired
    LikeRepository likeRepository;

    @Test
    void test() {
        likeRepository.addLike(3L, "lee");
    }
}