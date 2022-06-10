package com.term.moviesite.repository;

import com.term.moviesite.domain.Movies;
import com.term.moviesite.domain.Reviews;
import com.term.moviesite.domain.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReviewRepository {
    @PersistenceContext
    EntityManager em;

    public void addReview(String userId, Long movieId, String comment, short rate) {
        em.persist(new Reviews(em.find(Users.class, userId), em.find(Movies.class, movieId), comment, rate));
    }

    public List<Reviews> findReviewsByMovieId(Long movieId) {
        return em.createQuery("select r from Reviews r where r.movie.movieId=:movieId", Reviews.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }
}
