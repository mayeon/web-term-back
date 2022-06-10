package com.term.moviesite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private Short grade;
    private String comment;
    private String userName;
    private Integer likes;
}
