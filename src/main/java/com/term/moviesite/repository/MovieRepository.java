package com.term.moviesite.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.term.moviesite.domain.*;
import com.term.moviesite.dto.MovieDtoDetail;
import com.term.moviesite.dto.MovieDtoSimple;
import com.term.moviesite.dto.UserStats;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MovieRepository {
    @PersistenceContext
    EntityManager em;

    public void addMovie(Movies movie) {
        em.persist(movie);
    }

    public List<MovieDtoSimple> findMovies() {
        return em.createQuery("select new com.term.moviesite.dto.MovieDtoSimple(" +
                        "m.movieId, m.title, m.posterLink, m.reservationRate, m.grade" +
                        ") from Movies m", MovieDtoSimple.class)
                .getResultList();
    }

    public MovieDtoDetail findMovie(String title) {
        return em.createQuery("select new com.term.moviesite.dto.MovieDtoDetail(" +
                        "m.movieId, m.title, m.posterLink, m.director, m.openDate, m.genre, m.runningTime, m.reservationRate, m.grade, m.story" +
                        ") from Movies m where m.title=:title", MovieDtoDetail.class)
                .setParameter("title", title)
                .getResultList().get(0);
    }

    public List<UserStats> findUserStats(Long movieId) {
        List<UserStats> userStats = em.createQuery(
                "select distinct new com.term.moviesite.dto.UserStats(u.gender, u.age)" +
                        "from Movies m " +
//                        "left outer join m.screens s on m.movieId = s.movie.movieId " +
//                        "left outer join s.tickets t on s.screenId = t.screen.screenId " +
//                        "left outer join t.user u on t.user.userId = u.userId " +
                        "left outer join m.screens s " +
                        "left outer join s.tickets t " +
                        "left outer join t.user u " +
                        "where m.movieId=:inputMovieId"
//                        "where m.title=:inputMovieId"

                )
                .setParameter("inputMovieId", movieId)
//                .setParameter("inputMovieId", "닥터 스트레인지2")
                .getResultList();

        return userStats;
    }

    public List<MovieDtoSimple> findMovieByTitleOrActor(String movieTitle, String actorName) {
        QMovies movies = QMovies.movies;
        QMoviesActors moviesActors = QMoviesActors.moviesActors;
        QActors actors = QActors.actors;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<MovieDtoSimple> fetch = query.select(Projections.constructor(MovieDtoSimple.class,
                        movies.movieId, movies.title, movies.posterLink, movies.reservationRate, movies.grade, movies.story
                        )
                )
                .from(moviesActors)
                .join(moviesActors.movie, movies)
                .join(moviesActors.actor, actors)
                .where(eqTitle(movieTitle), eqActors(actorName))
                .distinct()
                .fetch();

        return fetch;
    }

    public BooleanExpression eqTitle(String movieName) {
        return (movieName == null || movieName.trim().equals("")) ? null : QMoviesActors.moviesActors.movie.title.eq(movieName);
    }

    public BooleanExpression eqActors(String actorName) { // 배우 유무
        return (actorName == null || actorName.trim().equals("")) ? null : QMoviesActors.moviesActors.actor.name.eq(actorName);
    }

}
