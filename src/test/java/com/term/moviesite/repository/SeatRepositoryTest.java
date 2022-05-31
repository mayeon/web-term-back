package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Seats;
import com.term.moviesite.domain.Tickets;
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
    void test() {
        List<Seats> fetch = seatRepository.findSeatByTicketId(2L);
        for (int i = 0; i < fetch.size(); i++) {
            Seats seats = fetch.get(i);
            System.out.println(seats.getSeatId());
            System.out.println(seats.getRow());
            System.out.println(seats.getCol());
            System.out.println();
        }
    }
}