package com.term.moviesite.web;

import com.term.moviesite.service.LikeService;
import com.term.moviesite.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final LikeService likeService;

    @PostMapping("/like")
    public void hitLike(@RequestBody ReviewLike reviewLike) {
        likeService.hitLike(reviewLike.getReviewId(), reviewLike.getUserId());
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewInfo reviewInfo) {
        reviewService.addReview(reviewInfo.getUserId(), reviewInfo.getMovieId(), reviewInfo.getComment(), reviewInfo.getRate());
    }

    @AllArgsConstructor
    @Getter
    static class ReviewLike {
        private String userId;
        private Long reviewId;
    }


    @AllArgsConstructor
    @Getter
    static class ReviewInfo {
        private String userId;
        private Long movieId;
        private String comment;
        private short rate;
    }
}
