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

    @Test
    void test() {
        List<Tickets> ticket = ticketRepository.findTicketByUserId("park");
        for (int i = 0; i < ticket.size(); i++) {
            System.out.println(ticket.get(i).getUser().getName() + " " + ticket.get(i).getScreen().getMovie().getTitle() + " " + ticket.get(i).getScreen().getStartTime());
        }
    }
}