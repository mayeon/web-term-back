package com.term.moviesite.web;

import com.term.moviesite.dto.SeatMatrix;
import com.term.moviesite.service.SeatService;
import com.term.moviesite.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final SeatService seatService;

    @PostMapping("/reservation")
    public void ticketing(@RequestBody TicketingInfo ticketingInfo) {
        Long ticketId = ticketService.ticketing(ticketingInfo.getScreenId(), ticketingInfo.getUserId());
        seatService.seatReservation(ticketId, ticketingInfo.getScreenId(), ticketingInfo.getSeatMatrices());
    }

    @AllArgsConstructor
    @Getter
    @ToString
    static class TicketingInfo {
        private String userId;
        private Long screenId;
        private List<SeatMatrix> seatMatrices;
    }
}
