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

    public boolean updateReview(String userId, Long reviewId, String comment, short rate) {
        Reviews review = em.find(Reviews.class, reviewId);
        if (userId == review.getUser().getUserId()) {
            review.setComment(comment);
            review.setRate(rate);
            return true;
        }
        return false;
    }

    public boolean deleteReview(String userId, Long reviewId) {
        Reviews review = em.find(Reviews.class, reviewId);
        if (userId == review.getUser().getUserId()) {
            em.remove(review);
            return true;
        }
        return false;
    }

    public List<Reviews> findReviewsByMovieId(Long movieId) {
        return em.createQuery("select r from Reviews r where r.movie.movieId=:movieId", Reviews.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }
}
