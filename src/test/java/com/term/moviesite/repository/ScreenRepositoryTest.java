package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Movies;
import com.term.moviesite.domain.Screens;
import com.term.moviesite.dto.ScreensDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ScreenRepositoryTest {
    @Autowired
    ScreenRepository screenRepository;

    @Test
    void test() {
        List<ScreensDto> allScreens = screenRepository.findAllScreens();
        for (int i = 0; i < allScreens.size(); i++) {
            System.out.println(allScreens.get(i).toString());
            System.out.println();
        }
    }
}
