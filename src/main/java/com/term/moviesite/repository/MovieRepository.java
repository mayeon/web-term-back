package com.term.moviesite.repository;

import com.term.moviesite.domain.Movies;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MovieRepository {
    @PersistenceContext
    EntityManager em;

//    public List<Movies> findAll() {
//        return em.createQuery("select m from movies m").getResultList();
//    }

//    public void findAll() {
//        List<Movies> ms = em.createQuery("select m from movies m").getResultList();
//        int i = 0;
//        for(Movies m: ms) {
//            System.out.println(ms.get(i++).getName());
//        }
//    }
}
