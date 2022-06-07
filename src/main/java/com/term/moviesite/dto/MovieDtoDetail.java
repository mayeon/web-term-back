package com.term.moviesite.dto;

import com.term.moviesite.domain.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDtoDetail {  // 제목, 포스터, 감독, 개봉일, 장르, 러닝타임, 예매율, 평점
    private String movieTitle;
    private String posterLink;
    private String director;
    private Date openDate;
    private Genre genre;
    private Short runningTime;
    private Float reservationRate;
    private Float grade;
    private String story;
}
