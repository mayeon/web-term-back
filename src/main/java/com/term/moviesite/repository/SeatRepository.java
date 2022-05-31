package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    public List<Seats> findSeatByTicketId(Long ticketId) {
        QSeats seats = QSeats.seats;
        QTicketsSeats ticketsSeats = QTicketsSeats.ticketsSeats;
        QTickets tickets = QTickets.tickets;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Seats> fetch = query.select(seats)
                .from(ticketsSeats)
                .join(ticketsSeats.seat, seats)
                .join(ticketsSeats.ticket, tickets)
                .where(tickets.ticketId.eq(ticketId))
                .fetch();

        return fetch;
    }
}
