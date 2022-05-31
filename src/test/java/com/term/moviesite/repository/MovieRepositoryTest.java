package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Actors;
import com.term.moviesite.domain.Movies;
import com.term.moviesite.domain.MoviesActors;
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
    void test() {
        List<String> query = movieRepository.findMovieByTitleOrActor("영화1", "");
        for (int i = 0; i < query.size(); i++) {
            System.out.println(query.get(i));
        }
    }
}