package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.term.moviesite.domain.*;
import com.term.moviesite.domain.enums.DiscountPolicy;
import com.term.moviesite.domain.enums.Genre;
import com.term.moviesite.dto.ScreensDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ScreenRepository {
    @PersistenceContext
    EntityManager em;

    public void addScreen(Screens screen) {
        em.persist(screen);
    }

    public List<ScreensDto> findAllScreens() { // 내일 하룻 동안만 상영하는 정보
        QScreens screens = QScreens.screens;
        QMovies movies = QMovies.movies;
        QTheaters theaters = QTheaters.theaters;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<ScreensDto> fetch = query.select(Projections.constructor(ScreensDto.class,
                        movies.title, movies.genre, movies.openDate, movies.runningTime, screens.theater, screens.startTime)
                )
                .from(screens)
                .join(screens.movie, movies)
                .join(screens.theater, theaters)
                .where(screens.startTime.between(Date.valueOf(LocalDate.now().plusDays(1)), Date.valueOf(LocalDate.now().plusDays(2))))
                .fetch();
        return fetch;
    }

    public void updateDiscountInfo(Long screenId, DiscountPolicy discountPolicy, short discountRate) {
        Screens screen = em.find(Screens.class, screenId);
        screen.setDiscountPolicy(discountPolicy);
        screen.setDiscountRate(discountRate);
    }

}
