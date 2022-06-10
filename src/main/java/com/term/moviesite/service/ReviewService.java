package com.term.moviesite.service;


import com.term.moviesite.domain.Reviews;
import com.term.moviesite.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void addReview(String userId, Long movieId, String comment, short rate) {
        reviewRepository.addReview(userId, movieId, comment, rate);
    }

    public List<Reviews> movieReviews(Long movieId) {
        return reviewRepository.findReviewsByMovieId(movieId);
    }
}
