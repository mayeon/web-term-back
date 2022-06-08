package com.term.moviesite.repository;

import com.term.moviesite.domain.Tickets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TicketRepositoryTest {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    SeatRepository seatRepository;

    @Test
    void ticketing() {
        Long ticketId = ticketRepository.ticketing(1L, "choi");
        System.out.println(ticketId);
    }

    @Test
    void findTickets() {
        String userId = "park";
        List<Tickets> userTickets = ticketRepository.findTicketByUserId(userId);

        for(Tickets userTicket: userTickets) {
            System.out.println("Ticket ID : " + userTicket.getTicketId());
        }
    }
}