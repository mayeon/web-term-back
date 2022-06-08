package com.term.moviesite.repository;

import com.term.moviesite.domain.Likes;
import com.term.moviesite.domain.Reviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LikeRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    LikeRepository likeRepository;

    @Test
    void hitLike() { // true = 좋아요 안누름 -> 누름 / false = 좋아요 누름 -> 취소
//        likeRepository.addLike(1L, "kim"); // 좋아요 안누른 사용자 / 기존 좋아요 수 = 1
        likeRepository.addLike(2L, "park"); // 좋아요 누른 사용자 / 기존 좋아요 수 = 2

        List<Reviews> reviews = reviewRepository.findReviewsByMovieId(1L);
        for(Reviews review: reviews) {
            System.out.println(review.getReviewId() + " " + review.getLike());
        }
    }
}