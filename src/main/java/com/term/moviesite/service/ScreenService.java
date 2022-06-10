package com.term.moviesite.service;

import com.term.moviesite.domain.enums.DiscountPolicy;
import com.term.moviesite.dto.MovieScreenDto;
import com.term.moviesite.dto.ScreenDto;
import com.term.moviesite.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository screenRepository;

    public Map<Long, MovieScreenDto> findAllScreens() {
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
        return screenMap;
    }

    public List<ScreenDto> findScreenByMovieId(Long movieId) {
        return screenRepository.findScreenByMovieId(movieId);
    }

    public Short findScreenPrice(Long screenId) {
        return screenRepository.findScreenPrice(screenId);
    }

    public void updateScreenDiscount(Long screenId, DiscountPolicy discountPolicy, Short discountRate) {
        screenRepository.updateDiscountInfo(screenId, discountPolicy, discountRate);
    }
}
