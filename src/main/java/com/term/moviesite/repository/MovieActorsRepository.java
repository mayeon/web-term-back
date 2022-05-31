package com.term.moviesite.repository;

import com.term.moviesite.domain.MoviesActors;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MovieActorsRepository {
    @PersistenceContext
    EntityManager em;

    public void addMovieActor(MoviesActors moviesActors) {
        em.persist(moviesActors);
    }
}
