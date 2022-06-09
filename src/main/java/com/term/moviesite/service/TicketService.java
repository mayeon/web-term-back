package com.term.moviesite.service;

import com.term.moviesite.domain.Tickets;
import com.term.moviesite.repository.SeatRepository;
import com.term.moviesite.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    public List<Tickets> findTicketByUserId(String userId) {
        return ticketRepository.findTicketByUserId(userId);
    }

    public Long ticketing(Long screenId, String userId) {
        return ticketRepository.ticketing(screenId, userId);
    }
}
