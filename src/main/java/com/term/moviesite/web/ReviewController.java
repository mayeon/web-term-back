package com.term.moviesite.web;

import com.term.moviesite.service.LikeService;
import com.term.moviesite.service.ReviewService;
import com.term.moviesite.token.JWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final LikeService likeService;
    private final JWT jwt;

    @PostMapping("/like")
    public void hitLike(@RequestBody ReviewLike reviewLike, HttpServletRequest request) {
        String userId = jwt.getUserId(request.getHeader(JWT.AUTHORIZATION_HEADER));
        likeService.hitLike(reviewLike.getReviewId(), userId);
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewInfo reviewInfo, HttpServletRequest request) {
        String userId = jwt.getUserId(request.getHeader(JWT.AUTHORIZATION_HEADER));
        reviewService.addReview(userId, reviewInfo.getMovieId(), reviewInfo.getComment(), reviewInfo.getRate());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    static class ReviewLike {
        private Long reviewId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    static class ReviewInfo {
        private Long movieId;
        private String comment;
        private short rate;
    }
}
