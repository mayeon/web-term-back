package com.term.moviesite.web;

import com.term.moviesite.domain.Reviews;
import com.term.moviesite.domain.enums.Gender;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/list/order/rate")
    public List<MovieDtoSimple> movieListOrderRate() {
        return movieService.findMovieListOrderByRate();
    }

    @GetMapping("/list/order/reservation")
    public List<MovieDtoSimple> movieListOrderReservation() {
        return movieService.findMovieListOrderByReservation();
    }

    @GetMapping("/list/{page}")
    public List<MovieDtoSimple> movieListPage(@PathVariable("page") int page) {
        return movieService.findMoviesPage(page);
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
    public Stats userStats(@PathVariable("id") Long movieId) {
        List<UserStats> userStatistics = movieService.findUserStats(movieId);
        int[] ageStats = new int[6]; // 0: 10대 이하, 1: 10대, ~~, 5: 50대 이상
        int[] genderStats = new int[2]; // 0: 남자, 1: 여자

        for(UserStats userStats: userStatistics) {
            if (userStats.getGender() == Gender.MALE) {
                genderStats[0]++;
            } else {
                genderStats[1]++;
            }

            for(int i = 0; i < 6; i++) {
                if(forGetUserStatsAge(i * 10, userStats.getAge())) {
                    ageStats[i]++;
                }
            }
        }

        Map<String, Integer> ageStatsMap = new HashMap<>();
        ageStatsMap.put("underTeens", ageStats[0]);
        ageStatsMap.put("Teens", ageStats[1]);
        ageStatsMap.put("Twenties", ageStats[2]);
        ageStatsMap.put("Thirties", ageStats[3]);
        ageStatsMap.put("Forties", ageStats[4]);
        ageStatsMap.put("upperFifties", ageStats[5]);

        Map<String, Integer> genderStatsMap = new HashMap<>();
        genderStatsMap.put("male", genderStats[0]);
        genderStatsMap.put("female", genderStats[1]);

        return new Stats(ageStatsMap, genderStatsMap);
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
        private Map<String, Integer> ageStatsMap;
        private Map<String, Integer> genderStatsMap;
    }

    boolean forGetUserStatsAge(int stdAge, int age) {
        if (stdAge <= age && age < stdAge + 10) {
            return true;
        }
        return false;
    }
}
