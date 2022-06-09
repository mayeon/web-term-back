package com.term.moviesite.repository;

import com.term.moviesite.domain.Reviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void test() {
        List<Reviews> reviewsByMovieId = reviewRepository.findReviewsByMovieId(1L);

        for (int i = 0; i < reviewsByMovieId.size(); i++) {
            System.out.println(reviewsByMovieId.get(i).getReviewId() + " " + reviewsByMovieId.get(i).getComment());
        }
    }
}