package com.term.moviesite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class MovieDtoSimple {
    private String movieTitle;
    private String posterLink;
    private Float reservationRate;
    private Float grade;
    private String story;

    public MovieDtoSimple(String movieTitle, String posterLink, Float reservationRate, Float grade, String story) { // 영화 검색
        this.movieTitle = movieTitle;
        this.posterLink = posterLink;
        this.reservationRate = reservationRate;
        this.grade = grade;
        this.story = story;
    }

    public MovieDtoSimple(String movieTitle, String posterLink, Float reservationRate, Float grade) { // 영화 목록
        this.movieTitle = movieTitle;
        this.posterLink = posterLink;
        this.reservationRate = reservationRate;
        this.grade = grade;
    }
}
