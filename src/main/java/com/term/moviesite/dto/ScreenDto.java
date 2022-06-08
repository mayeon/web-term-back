package com.term.moviesite.dto;

import com.term.moviesite.domain.Theaters;
import com.term.moviesite.domain.enums.Genre;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScreenDto { // 상영 시간표 페이지, 예매 페이지
    private Long screenId;
    private String title;
    private Long movieId;
    private Genre genre;
    private Date openDate;
    private Short runningTime;
    private Theaters theater;
    private Date startTime;
    private Short price;
}
