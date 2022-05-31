package com.term.moviesite.repository;

import com.term.moviesite.domain.Reviews;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReviewRepository {
    @PersistenceContext
    EntityManager em;

    public void addReview(Reviews review) {
        em.persist(review);
    }

    public List<Reviews> findReviewsByMovieId(Long movieId) {
        return em.createQuery("select r from Reviews r where r.movie.movieId=:movie_id", Reviews.class)
                .setParameter("movie_id", movieId)
                .getResultList();
    }
}
