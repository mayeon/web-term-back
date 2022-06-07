package com.term.moviesite.repository;

import com.term.moviesite.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SeatRepository {
    @PersistenceContext
    EntityManager em;

    public void addSeat(Seats seat) {
        em.persist(seat);
    }

    public List<Seats> findSeatsByTicketId(Long ticketId) {
        List<Seats> seats = em.createQuery("select s from Seats s where s.ticket.ticketId=:ticketId")
                .setParameter("ticketId", ticketId)
                .getResultList();
        return seats;
    }
}
