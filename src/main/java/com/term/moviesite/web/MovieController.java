package com.term.moviesite.web;

import com.term.moviesite.domain.Reviews;
import com.term.moviesite.dto.MovieDtoDetail;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.ReviewDto;
import com.term.moviesite.dto.UserStats;
import com.term.moviesite.service.MovieService;
import com.term.moviesite.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping("/list")
    public List<MovieDtoSimple> movieList() {
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

    @GetMapping("/detail/{id}/stats")
    public List<UserStats> userStats(@PathVariable("id") Long movieId) {
        return movieService.findUserStats(movieId);
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
}
