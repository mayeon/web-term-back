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

    @PostMapping("/update")
    public boolean updateReview(@RequestBody ReviewInfo reviewInfo, HttpServletRequest request) {
        String userId = jwt.getUserId(request.getHeader(JWT.AUTHORIZATION_HEADER));
        return reviewService.updateReview(userId, reviewInfo.getReviewId(), reviewInfo.getComment(), reviewInfo.getRate());
    }

    @PostMapping("/delete")
    public boolean deleteReview(@RequestBody ReviewInfo reviewInfo, HttpServletRequest request) {
        String userId = jwt.getUserId(request.getHeader(JWT.AUTHORIZATION_HEADER));
        return reviewService.deleteReview(userId, reviewInfo.getReviewId());
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
        private Long reviewId;
        private Long movieId;
        private String comment;
        private short rate;

        public ReviewInfo(Long reviewId) {
            this.reviewId = reviewId;
        }

        public ReviewInfo(Long reviewId, String comment, short rate) {
            this.reviewId = reviewId;
            this.comment = comment;
            this.rate = rate;
        }
    }
}
