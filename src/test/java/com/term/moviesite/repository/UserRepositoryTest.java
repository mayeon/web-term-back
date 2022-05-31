package com.term.moviesite.repository;

import com.querydsl.core.Tuple;
import com.term.moviesite.domain.Seats;
import com.term.moviesite.domain.Tickets;
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
    @Autowired
    SeatRepository seatRepository;

    @Test
    void test() throws Exception {
        String userId = "park";
        String userPw = "123";
//        Users user = new Users(userId, userPw, "park", (short)22, Gender.MALE, false);
//        userRepository.addUser(user);

        System.out.println(userRepository.findUser(userId));
        System.out.println(userRepository.findUser("123"));

        System.out.println(userRepository.findUserIdAndPw(userId, userPw));
        System.out.println(userRepository.findUserIdAndPw(userId, "312"));
    }

    @Test
    void findSeat() {
        List<Tickets> userId = ticketRepository.findTicketByUserId("park");
        List<Seats> seatInfo = seatRepository.findSeatByTicketId(userId.get(0).getTicketId());
        for (int i = 0; i < seatInfo.size(); i++) {
            System.out.println(seatInfo.get(i).getSeatId());
            System.out.println(seatInfo.get(i).getRow());
            System.out.println(seatInfo.get(i).getCol());
            System.out.println();
        }
    }

}