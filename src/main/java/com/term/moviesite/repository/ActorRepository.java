package com.term.moviesite.repository;

import com.term.moviesite.domain.Actors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ActorRepository {
    @Autowired
    EntityManager em;

    public void addActor(Actors actor) {
        em.persist(actor);
    }
}
