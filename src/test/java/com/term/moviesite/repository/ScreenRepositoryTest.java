package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Movies;
import com.term.moviesite.domain.Screens;
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
        List<Tuple> allScreens = screenRepository.findAllScreens();
        System.out.println(allScreens.get(0).toArray().length);
        System.out.println(allScreens.get(0).toArray()[0]);
        System.out.println(allScreens.get(0).toArray()[1]);
        for (int i = 0; i < allScreens.size(); i++) {
            Screens s = (Screens)allScreens.get(i).toArray()[0];
            Movies m = (Movies)allScreens.get(i).toArray()[1];
//            System.out.println(m.getTitle());
//            System.out.println(s.getScreenId());
            System.out.println(s.getTheater().getTheaterName());
            System.out.println();
        }
    }
}
