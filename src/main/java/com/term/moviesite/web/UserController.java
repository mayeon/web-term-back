package com.term.moviesite.web;

import com.term.moviesite.domain.*;
import com.term.moviesite.dto.SeatMatrix;
import com.term.moviesite.service.TicketService;
import com.term.moviesite.service.UserService;
import com.term.moviesite.token.JWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;
    private final JWT jwt;

    @GetMapping("/info")
    public UserInfo userInfo(HttpServletRequest request) {
        String userId = jwt.getUserId(request.getHeader(JWT.AUTHORIZATION_HEADER));
        List<Tickets> findUserTickets = ticketService.findTicketByUserId(userId);
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

        Users findUser = userService.findUserById(userId);
        return new UserInfo(findUser.getName(), userTickets);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    static class UserInfo<T> {
        private String name;
        private T ticketData;
    }

    @NoArgsConstructor
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
