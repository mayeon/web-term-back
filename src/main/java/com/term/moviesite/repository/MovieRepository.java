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

    public MovieDtoDetail findMovie(Long movieId) {
        return em.createQuery("select new com.term.moviesite.dto.MovieDtoDetail(" +
                        "m.movieId, m.title, m.posterLink, m.director, m.openDate, m.genre, m.runningTime, m.reservationRate, m.grade, m.story" +
                        ") from Movies m where m.movieId=:movieId", MovieDtoDetail.class)
                .setParameter("movieId", movieId)
                .getResultList().get(0);
    }

    public List<UserStats> findUserStats(Long movieId) {
        return em.createQuery(
                "select new com.term.moviesite.dto.UserStats(u.gender, u.age)" +
                        "from Movies m " +
//                        "left outer join m.screens s on m.movieId = s.movie.movieId " +
//                        "left outer join s.tickets t on s.screenId = t.screen.screenId " +
//                        "left outer join t.user u on t.user.userId = u.userId " +
                        "left outer join m.screens s " +
                        "left outer join s.tickets t " +
                        "left outer join t.user u " +
                        "where m.movieId=:inputMovieId and not u.gender is null"

                )
                .setParameter("inputMovieId", movieId)
                .getResultList();
    }

    public List<MovieDtoSimple> findMovieByTitleOrActor(String movieOrActor) {
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
                .where(eqTitle(movieOrActor).or(eqActors(movieOrActor)))
                .distinct()
                .fetch();

        return fetch;
    }

    public BooleanExpression eqTitle(String movieOrActor) {
        return (movieOrActor == null || movieOrActor.trim().equals("")) ? null : QMoviesActors.moviesActors.movie.title.eq(movieOrActor);
    }

    public BooleanExpression eqActors(String movieOrActor) { // 배우 유무
        return (movieOrActor == null || movieOrActor.trim().equals("")) ? null : QMoviesActors.moviesActors.actor.name.eq(movieOrActor);
    }
}
