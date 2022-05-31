package com.term.moviesite.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.term.moviesite.domain.*;
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

    // TODO 아래 3개 별도의 dto 생성 후 전송
    public List<Movies> findMovies() { // 제목, 포스터, 예매율, 평점
        return em.createQuery("select m from Movies m", Movies.class)
                .getResultList();
    }

    public Movies findMovie(String title) { // 제목, 포스터, 감독, 개봉일, 장르, 러닝타임, 예매율, 평점
        return em.createQuery("select m from Movies m where m.title=:title", Movies.class)
                .setParameter("title", title)
                .getResultList().get(0);
    }

    public List<String> findMovieByTitleOrActor(String movieTitle, String actorName) { // 제목, 포스터, 예매율, 평점
        QMovies movies = QMovies.movies;
        QMoviesActors moviesActors = QMoviesActors.moviesActors;
        QActors actors = QActors.actors;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<String> fetch = query.select(movies.title)
                .from(moviesActors)
                .join(moviesActors.movie, movies)
                .join(moviesActors.actor, actors)
                .where(eqTitle(movieTitle), eqActors(actorName))
                .distinct()
                .fetch();

        return fetch;

//        JPAQueryFactory query = new JPAQueryFactory(em);
//        List<Tuple> fetch = query.select(movies, moviesActors)
//                .from(moviesActors)
//                .join(moviesActors.movie, movies)
//                .join(moviesActors.actor, actors)
//                .where(eqActors(actorName), movies.title.eq(movieTitle))
//                .fetchJoin()
//                .fetch();
//
//        for(int i = 0; i < fetch.size(); i++) {
//            System.out.println(((Movies)fetch.get(i).toArray()[0]).getTitle());
//        }
    }

    public BooleanExpression eqTitle(String movieName) {
        return (movieName == null || movieName.trim().equals("")) ? null : QMoviesActors.moviesActors.movie.title.eq(movieName);
    }

    public BooleanExpression eqActors(String actorName) { // 배우 유무
        return (actorName == null || actorName.trim().equals("")) ? null : QMoviesActors.moviesActors.actor.name.eq(actorName);
    }

}
