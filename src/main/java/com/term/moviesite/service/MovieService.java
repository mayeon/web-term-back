package com.term.moviesite.service;

import com.term.moviesite.dto.MovieDtoDetail;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.UserStats;
import com.term.moviesite.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MovieDtoSimple> findMovieList() {
        return movieRepository.findMovies();
    }

    public List<MovieDtoSimple> findMovieListOrderByRate() {
        return movieRepository.findMoviesOrderRate();
    }

    public List<MovieDtoSimple> findMovieListOrderByReservation() {
        return movieRepository.findMoviesOrderReservation();
    }

    public List<MovieDtoSimple> findMoviesPage(int page) {return movieRepository.findMoviesPage(page);}

    public MovieDtoDetail findMovie(Long movieId) {
        return movieRepository.findMovie(movieId);
    }

    public List<MovieDtoSimple> searchMovie(String movieOrActor) {
        if (movieOrActor.equals("")) {
            return null;
        }
        return movieRepository.findMovieByTitleOrActor(movieOrActor);
    }

    public List<UserStats> findUserStats(Long movieId) {
        return movieRepository.findUserStats(movieId);
    }
}
