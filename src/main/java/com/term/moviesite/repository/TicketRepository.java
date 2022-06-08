package com.term.moviesite.repository;

import com.term.moviesite.domain.Screens;
import com.term.moviesite.domain.Tickets;
import com.term.moviesite.domain.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TicketRepository {
    @PersistenceContext
    EntityManager em;

    public Long ticketing(Long screenId, String userId) {
        Users user = em.find(Users.class, userId);
        Screens screen = em.find(Screens.class, screenId);
        Tickets ticket = new Tickets(user, screen);
        em.persist(ticket);
        em.flush();
        return ticket.getTicketId();
    }

    public void cancelTicketing(Long ticketId) {
        Tickets ticket = em.find(Tickets.class, ticketId);
        em.remove(ticket);
    }

    public List<Tickets> findTicketByUserId(String userId) {
        return em.createQuery("select t from Tickets t where t.user.userId=:userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
