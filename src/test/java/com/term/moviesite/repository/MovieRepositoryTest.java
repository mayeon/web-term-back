package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Actors;
import com.term.moviesite.domain.Movies;
import com.term.moviesite.domain.MoviesActors;
import com.term.moviesite.domain.enums.Gender;
import com.term.moviesite.dto.MovieDtoDetail;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.UserStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Long movieId = 1L;
        MovieDtoDetail detail = movieRepository.findMovie(movieId);
        System.out.println(detail);
    }

    @Test
    void movieSearch() {
        String movieName = "이미테이션 게임";
        String actorName = "마동석";
        List<MovieDtoSimple> query = movieRepository.findMovieByTitleOrActor(actorName);
        if(query != null) {
            for (int i = 0; i < query.size(); i++) {
                System.out.println(query.get(i).toString());
            }
        } else {
            System.out.println("null");
        }
    }

    @Test
    void getUserStats() { // TODO 성별, 나이 그룹화 (~10~50~) 로직 여기에 구현됨
        List<UserStats> userStatistics = movieRepository.findUserStats(1L);
        int[] genderStats = new int[2]; // 0: 남자, 1: 여자
        int[] ageStats = new int[6]; // 0: 10대 이하, 1: 10대, ~~, 5: 50대 이상

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

        System.out.println("남 : " + genderStats[0]);
        System.out.println("여 : " + genderStats[1]);

        System.out.println("10대 미만 : " + ageStats[0]);
        System.out.println("10대 : " + ageStats[1]);
        System.out.println("20대 : " + ageStats[2]);
        System.out.println("30대 : " + ageStats[3]);
        System.out.println("40대 : " + ageStats[4]);
        System.out.println("50대 이상 : " + ageStats[5]);
    }

    boolean forGetUserStatsAge(int stdAge, int age) {
        if (stdAge <= age && age < stdAge + 10) {
            return true;
        }
        return false;
    }
}