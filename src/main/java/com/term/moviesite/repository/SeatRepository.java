package com.term.moviesite.repository;

import com.term.moviesite.domain.*;
import com.term.moviesite.dto.SeatMatrix;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SeatRepository {
    @PersistenceContext
    EntityManager em;

    public void seatReservation(Long ticketId, Long screenId, List<SeatMatrix> seatInfos) {
        Tickets ticket = em.find(Tickets.class, ticketId);
        Screens screen = em.find(Screens.class, screenId);
        for(SeatMatrix seatInfo: seatInfos) {
            System.out.println(seatInfo.getRow());
            System.out.println(seatInfo.getCol());
            Seats seat = new Seats(ticket, screen, seatInfo.getRow(), seatInfo.getCol());
            seat.setReserved(true);
            em.persist(seat);
        }
    }

    public List<Seats> findSeatsByScreenId(Long screenId) {
        List<Seats> seats = em.createQuery("select s from Seats s where s.screen.screenId=:screenId")
                .setParameter("screenId", screenId)
                .getResultList();
        return seats;
    }

    public List<Seats> findSeatsByTicketId(Long ticketId) {
        List<Seats> seats = em.createQuery("select s from Seats s where s.ticket.ticketId=:ticketId")
                .setParameter("ticketId", ticketId)
                .getResultList();
        return seats;
    }
}
