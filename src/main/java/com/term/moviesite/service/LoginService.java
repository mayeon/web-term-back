package com.term.moviesite.service;

import com.term.moviesite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService { // TODO 구현
    private final UserRepository userRepository;

    public boolean
}
