package com.term.moviesite.repository;

import com.term.moviesite.domain.Tickets;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TicketRepository {
    @PersistenceContext
    EntityManager em;

    public void addTicket(Tickets ticket) {
        em.persist(ticket);
    }

    public List<Tickets> findTicketByUserId(String userId) {
        return em.createQuery("select t from Tickets t where t.user.userId=:userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
