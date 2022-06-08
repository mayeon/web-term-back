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
    void getUserStats() { // TODO 성별, 나이 그룹화 (~10~50~) 로직
        List<UserStats> userStatistics = movieRepository.findUserStats(1L);
        Map<String, Integer[]> stats = new HashMap<>();
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
    }

    boolean forGetUserStatsAge(int stdAge, int age) {
        if (stdAge <= age && age < stdAge + 10) {
            return true;
        }
        return false;
    }
}