package com.term.moviesite.web;

import com.term.moviesite.dto.SeatMatrix;
import com.term.moviesite.service.SeatService;
import com.term.moviesite.service.TicketService;
import com.term.moviesite.token.JWT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final SeatService seatService;
    private final JWT jwt;

    @PostMapping("/reservation")
    public boolean ticketing(@RequestBody TicketingInfo ticketingInfo, HttpServletRequest request) {
        String userId = jwt.getUserId(request.getHeader(JWT.AUTHORIZATION_HEADER));
        Long ticketId = ticketService.ticketing(ticketingInfo.getScreenId(), userId);
        seatService.seatReservation(ticketId, ticketingInfo.getScreenId(), ticketingInfo.getSeatMatrices());
        return true;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    static class TicketingInfo {
        private Long screenId;
        private List<SeatMatrix> seatMatrices;
    }
}
