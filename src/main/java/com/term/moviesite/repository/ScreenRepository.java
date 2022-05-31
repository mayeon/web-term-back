package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.term.moviesite.domain.QMovies;
import com.term.moviesite.domain.QScreens;
import com.term.moviesite.domain.QTheaters;
import com.term.moviesite.domain.Screens;
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

    // TODO 별도의 dto 생성 (제목, 장르, 런타임, 개봉일, 상영관 정보, 상영 시간)
   public List<Tuple> findAllScreens() { // 내일 하룻 동안만 상영하는 정보
       QScreens screens = QScreens.screens;
       QMovies movies = QMovies.movies;
       QTheaters theaters = QTheaters.theaters;

       JPAQueryFactory query = new JPAQueryFactory(em);
       List<Tuple> fetch = query.select(screens, movies)
               .from(screens)
               .join(screens.movie, movies)
               .join(screens.theater, theaters)
               .where(screens.startTime.between(Date.valueOf(LocalDate.now().plusDays(1)), Date.valueOf(LocalDate.now().plusDays(2))))
               .fetchJoin()
               .fetch();

       return fetch;

//            return em.createQuery("select s from Screens s where :startTime1 <= s.startTime and s.startTime <:startTime2 order by s.movie.title ASC")
//               .setParameter("startTime1", Date.valueOf(LocalDate.now().plusDays(1)))
//               .setParameter("startTime2", Date.valueOf(LocalDate.now().plusDays(2)))
//               .getResultList();
    }


}
