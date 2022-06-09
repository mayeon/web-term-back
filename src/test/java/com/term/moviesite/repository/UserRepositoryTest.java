package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Seats;
import com.term.moviesite.domain.Tickets;
import com.term.moviesite.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;

    @Test
    void getUserInfo() {
        String userId = "park";
        Users userInfo = userRepository.findUserInfo(userId);
        System.out.println("user id : " + userInfo.getUserId() + " / user name : " + userInfo.getName());
    }

    @Test
    void getUserTicket() {
        String userId = "park";
        List<Tickets> tickets = ticketRepository.findTicketByUserId(userId);
        for(Tickets ticket: tickets) {
            System.out.println(ticket.getUser().getUserId() + " " + ticket.getTicketId());
        }
    }
}