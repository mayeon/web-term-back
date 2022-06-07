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

    // TODO 해당 리뷰 좋아요 누른 사용자 -> false, 안누른 사용자 -> true 및 좋아요 개수 증가
    public boolean addLike(Long reviewId, String userId) {

        return false; // 이미 종아요 한 사용자
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
