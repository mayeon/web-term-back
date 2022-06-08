package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.term.moviesite.domain.*;
import com.term.moviesite.dto.MovieDtoSimple;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LikeRepository {
    @PersistenceContext
    EntityManager em;

    public void addLike(Long reviewId, String userId) {
        List<Likes> likes = em.createQuery("select l from Likes l where l.review.reviewId=:reviewId and l.user.userId=:userId")
                .setParameter("reviewId", reviewId)
                .setParameter("userId", userId)
                .getResultList();

        Reviews review = em.find(Reviews.class, reviewId);
        if(likes.isEmpty()) {
            Users user = em.find(Users.class, userId);
            em.persist(new Likes(review, user));
        } else {
            em.remove(likes.get(0));
        }
        em.flush();
    }

//    public void removeLike(Long reviewId, String userId) {
//        Likes like = (Likes) em.createQuery("select l from Likes l where l.review_id=:reviewId and l.user_id=:userId")
//                .setParameter("reviewId", reviewId)
//                .setParameter("userId", userId)
//                .getResultList().get(0);
//
//        if (like != null) {
//            em.remove(like);
//
//            Reviews review = em.find(Reviews.class, reviewId);
//            review.removeLike();
//        }
//    }
}
