package com.term.moviesite.service;

import com.term.moviesite.dto.MovieDtoDetail;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.UserStats;
import com.term.moviesite.repository.MovieRepository;
import com.term.moviesite.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public List<MovieDtoSimple> movieList() {
        return movieRepository.findMovies();
    }

    public List<MovieDtoSimple> searchMovie(String movieTitle, String actorName) {
        if (movieTitle.equals("") && actorName.equals("")) {
            return null;
        }
        return movieRepository.findMovieByTitleOrActor(movieTitle, actorName);
    }

    public List<UserStats> userStats(Long movieId) {
        return movieRepository.findUserStats(movieId);
    }
}
