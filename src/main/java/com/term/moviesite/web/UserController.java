package com.term.moviesite.web;

import com.term.moviesite.domain.*;
import com.term.moviesite.dto.SeatMatrix;
import com.term.moviesite.service.TicketService;
import com.term.moviesite.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;

    @PostMapping("/info")
    public UserInfo userInfo(@RequestBody Users user) {
        List<Tickets> findUserTickets = ticketService.findTicketByUserId(user.getUserId());
        List<UserTicketInfo> userTickets = new ArrayList<>();

        for (Tickets findUserTicket: findUserTickets) {
            List<SeatMatrix> seatMatrix = new ArrayList<>();

            for (Seats seat: findUserTicket.getSeats()) {
                seatMatrix.add(new SeatMatrix(seat.getRow(), (short)seat.getCol()));
            }

            Screens screenInfo = findUserTicket.getScreen();
            Theaters theaterInfo = screenInfo.getTheater();
            userTickets.add(new UserTicketInfo(findUserTicket.getTicketId(), screenInfo.getMovie().getTitle(), theaterInfo.getTheaterName(), theaterInfo.getFloor(), screenInfo.getStartTime(), seatMatrix));
        }

        Users findUser = userService.findUserById(user.getUserId());
        return new UserInfo(findUser.getName(), userTickets);
    }

    @AllArgsConstructor
    @Getter
    static class UserInfo<T> {
        private String name;
        private T ticketData;
    }

    @AllArgsConstructor
    @Getter
    static class UserTicketInfo {
        private Long ticketId;
        private String movieName;
        private String screenName;
        private Short screenFloor;
        private Date startDate;
        private List<SeatMatrix> seats;
    }
}
