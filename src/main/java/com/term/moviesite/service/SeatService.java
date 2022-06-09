package com.term.moviesite.service;

import com.term.moviesite.domain.Seats;
import com.term.moviesite.dto.SeatMatrix;
import com.term.moviesite.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public void seatReservation(Long ticketId, Long screenId, List<SeatMatrix> seatInfos) {
        seatRepository.seatReservation(ticketId, screenId, seatInfos);
    }

    public List<Seats> findSeatByScreenId(Long screenId) {
        return seatRepository.findSeatsByScreenId(screenId);
    }

    public List<Seats> findSeatByTicketId(Long ticketId) {
        return seatRepository.findSeatsByTicketId(ticketId);
    }
}
