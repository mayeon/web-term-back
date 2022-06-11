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
public class MovieDtoDetail {  // 영화 상세 정보 (상세 페이지)
    private Long movieId;
    private String movieTitle;
    private Short ageCut;
    private String posterLink;
    private String director;
    private Date openDate;
    private Genre genre;
    private Short runningTime;
    private Float reservationRate;
    private Float grade;
    private String story;
}
