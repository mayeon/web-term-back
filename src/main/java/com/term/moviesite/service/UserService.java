package com.term.moviesite.service;

import com.term.moviesite.domain.Users;
import com.term.moviesite.repository.SeatRepository;
import com.term.moviesite.repository.TicketRepository;
import com.term.moviesite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users findUserById(String userId) {
        return userRepository.findUserInfo(userId);
    }
}
