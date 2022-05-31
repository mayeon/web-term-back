package com.term.moviesite.repository;

import com.term.moviesite.domain.TicketsSeats;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TicketSeatRepository {
    @PersistenceContext
    EntityManager em;

    public void addTicketSeat(TicketsSeats ticketsSeats) {
        em.persist(ticketsSeats);
    }
}
