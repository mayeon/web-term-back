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

    public boolean updateReview(String userId, Long reviewId, String comment, short rate) {
        return reviewRepository.updateReview(userId, reviewId, comment, rate);
    }

    public boolean deleteReview(String userId, Long reviewId) {
        return reviewRepository.deleteReview(userId, reviewId);
    }

    public List<Reviews> movieReviews(Long movieId) {
        return reviewRepository.findReviewsByMovieId(movieId);
    }
}
