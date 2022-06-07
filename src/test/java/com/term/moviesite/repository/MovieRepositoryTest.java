package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Actors;
import com.term.moviesite.domain.Movies;
import com.term.moviesite.domain.MoviesActors;
import com.term.moviesite.dto.MovieDtoDetail;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.UserStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    void getSimpleMovieList() {
        List<MovieDtoSimple> simple = movieRepository.findMovies();
        for(MovieDtoSimple simpleInfo: simple) {
            System.out.println(simpleInfo);
        }
    }

    @Test
    void getDetailMovieInfo() {
        String movieName = "범죄도시2";
        MovieDtoDetail detail = movieRepository.findMovie(movieName);
        System.out.println(detail);
    }

    @Test
    void movieSearch() {
        String movieName = "";
        String actorName = "";
        if (!(movieName.equals("") && actorName.equals(""))) {
            List<MovieDtoSimple> query = movieRepository.findMovieByTitleOrActor(movieName, actorName);
            for (int i = 0; i < query.size(); i++) {
                System.out.println(query.get(i).toString());
            }
        } else {
            System.out.println("null");
        }
    }

    @Test
    void getUserStats() {
        List<UserStats> userStatses = movieRepository.findUserStats(1L);

        for(UserStats userStats: userStatses) {
            System.out.println("user gender : " + userStats.getGender() + " / user age : " + userStats.getAge());
        }
    }
}