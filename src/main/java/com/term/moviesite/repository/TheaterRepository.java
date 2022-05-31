package com.term.moviesite.repository;

import com.term.moviesite.domain.Theaters;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TheaterRepository {
    @PersistenceContext
    EntityManager em;

    public void addTheater(Theaters theater) {
        em.persist(theater);
    }

    public Theaters findById(Long theaterId) {
        return em.find(Theaters.class, theaterId);
    }
}
