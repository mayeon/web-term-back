package com.term.moviesite.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.term.moviesite.domain.*;
import com.term.moviesite.domain.enums.DiscountPolicy;
import com.term.moviesite.dto.ScreenDto;
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

    public Screens findScreenById(Long screenId) {
        return em.find(Screens.class, screenId);
    }

    public List<ScreenDto> findScreenByMovieId(Long movieId) {
        return em.createQuery("select s from Screens s where s.movie.movieId=:movieId")
                .setParameter("movieId", movieId)
                .getResultList();
    }

    public List<ScreenDto> findScreens() { // 내일 하룻 동안만 상영하는 정보
        QScreens screens = QScreens.screens;
        QMovies movies = QMovies.movies;
        QTheaters theaters = QTheaters.theaters;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<ScreenDto> fetch = query.select(Projections.constructor(ScreenDto.class,
                        screens.screenId, movies.title, movies.movieId, movies.genre, movies.openDate, movies.runningTime, screens.theater, screens.startTime, screens.price)
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
