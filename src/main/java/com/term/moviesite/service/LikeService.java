package com.term.moviesite.service;

import com.term.moviesite.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public void hitLike(Long reviewId, String userId) {
        likeRepository.addLike(reviewId, userId);
    }
}
