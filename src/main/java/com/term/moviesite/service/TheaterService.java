package com.term.moviesite.service;

import com.term.moviesite.domain.Theaters;
import com.term.moviesite.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public Theaters findTheater(Long theaterId) {
        return theaterRepository.findById(theaterId);
    }
}
