package com.term.moviesite.repository;

import com.term.moviesite.domain.Seats;
import com.term.moviesite.dto.SeatMatrix;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SeatRepositoryTest {
    @Autowired
    SeatRepository seatRepository;

    @Test
    void ticketing() {
        Long ticketId = 7L;
        Long screenId = 1L;
        List<SeatMatrix> sm = new ArrayList<>();
        sm.add(new SeatMatrix('A', (short)6));
        sm.add(new SeatMatrix('A', (short)7));
        seatRepository.seatReservation(ticketId, screenId, sm);
    }

    @Test
    void findSeatsByScreenId() {
        Long screenId = 1L;
        List<Seats> seats =seatRepository.findSeatsByScreenId(screenId);

        for(Seats seat: seats) {
            System.out.println("  Seat ID : " + seat.getSeatId() + " / Seat row : " + seat.getRow() + " / Seat col : " + seat.getCol());
        }
        System.out.println("");
    }

    @Test
    void findSeatsByTicketId() {
        Long ticketId = 1L;
        List<Seats> seats = seatRepository.findSeatsByTicketId(ticketId);

        for(Seats seat: seats) {
            System.out.println("  Seat ID : " + seat.getSeatId() + " / Seat row : " + seat.getRow() + " / Seat col : " + seat.getCol());
        }
        System.out.println("");
    }
}