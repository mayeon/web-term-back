package com.term.moviesite.repository;

import com.term.moviesite.domain.Screens;
import com.term.moviesite.domain.enums.DiscountPolicy;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.MovieScreenDto;
import com.term.moviesite.dto.ScreenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
class ScreenRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreenRepository screenRepository;

    @Test
    void findScreenByMovieId() {
        Long movieId = 4L;
        List<ScreenDto> screens = screenRepository.findScreenByMovieId(movieId);
        for (ScreenDto screen: screens) {
            System.out.println(screen.toString());
        }
    }

    @Test
    void findScreens() { // 내일 상영하는 모든 상영들 // TODO 상영 시간표 로직 여기에 구현됨
        List<ScreenDto> screens = screenRepository.findScreens();
        Map<Long, MovieScreenDto> screenMap = new HashMap<>();

        for (int i = 0; i < screens.size(); i++) {
            Long movieId = screens.get(i).getMovieId();
            if (screenMap.get(movieId) == null) {
                List<ScreenDto> screen = new ArrayList<>();
                screen.add(screens.get(i));
                screenMap.put(movieId, new MovieScreenDto(movieId, screens.get(i).getTitle(), screen));
            } else {
                MovieScreenDto msd = screenMap.get(movieId);
                List screen = msd.getScreens();
                screen.add(screens.get(i));
                msd.setScreens(screen);
                screenMap.replace(movieId, msd);
            }
        }

        for(int i = 1; i < 12; i++) {
            MovieScreenDto msd = screenMap.get((long)i);
            if(msd != null) {
                System.out.println("id : " + msd.getMovieId() + " / title : " + msd.getMovieName());
                for (int j = 0; j < msd.getScreens().size(); j++) {
                    System.out.println("    screen id : " + msd.getScreens().get(j).getScreenId());
                }
                System.out.println("----------------------------------------------------------------");
            }
        }
    }

    @Test
    void changeDiscountRate() {
        screenRepository.updateDiscountInfo(1L, DiscountPolicy.FIXED_AMOUNT, (short)4000);
        Screens screen1 = screenRepository.findScreenById(1L);
        System.out.println(screen1.getPrice());

        screenRepository.updateDiscountInfo(2L, DiscountPolicy.RATED_AMOUNT, (short)10);
        Screens screen2 = screenRepository.findScreenById(2L);
        System.out.println(screen2.getPrice());
    }
}
