package com.term.moviesite.web;

import com.term.moviesite.domain.Reviews;
import com.term.moviesite.dto.*;
import com.term.moviesite.service.MovieService;
import com.term.moviesite.service.ReviewService;
import com.term.moviesite.token.JWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping("/list")
    public List<MovieDtoSimple> movieList(HttpServletRequest request) {
        return movieService.findMovieList();
    }

    @GetMapping("/search")
    public List<MovieDtoSimple> searchMovie(@RequestParam(name = "query") String movieOrActor) {
        return movieService.searchMovie(movieOrActor);
    }

    @GetMapping("/detail/{id}/info")
    public MovieDtoDetail movieDetail(@PathVariable("id") Long movieId) {
        return movieService.findMovie(movieId);
    }

    @GetMapping("/detail/{id}/stats") // TODO 나이 그룹화, 성별 그룹화
    public List<UserStats> userStats(@PathVariable("id") Long movieId) {
        List<UserStats> userStats = movieService.findUserStats(movieId);
        if (userStats != null)
            return movieService.findUserStats(movieId);
        else
            return null;
    }

    @GetMapping("/detail/{id}/review")
    public List<ReviewDto> movieReviews(@PathVariable("id") Long movieId) {
        List<Reviews> findReviews = reviewService.movieReviews(movieId);
        List<ReviewDto> reviews = new ArrayList<>();
        for (Reviews findReview: findReviews) {
            reviews.add(new ReviewDto(findReview.getReviewId(), findReview.getRate(), findReview.getComment(), findReview.getUser().getName(), findReview.getLike()));
        }

        return reviews;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Stats {
        private Map<String, Integer> ageStats;
        private Map<String, Integer> genderStats;
    }
}
