package com.term.moviesite.repository;

import com.term.moviesite.domain.Seats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SeatRepositoryTest {
    @Autowired
    SeatRepository seatRepository;

    @Test
    void findSeats() {
        Long ticketId = 1L;
        List<Seats> seats = seatRepository.findSeatsByTicketId(ticketId);

        for(Seats seat: seats) {
            System.out.println("  Seat ID : " + seat.getSeatId() + " / Seat row : " + seat.getRow() + " / Seat col : " + seat.getCol());
        }
        System.out.println("");
    }
}