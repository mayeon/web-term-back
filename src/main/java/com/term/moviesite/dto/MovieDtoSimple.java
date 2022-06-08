package com.term.moviesite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class MovieDtoSimple { // 영화 목록, 영화 검색에서 사용
    private Long movieId;
    private String movieTitle;
    private String posterLink;
    private Float reservationRate;
    private Float grade;
    private String story;

    public MovieDtoSimple(Long movieId, String movieTitle, String posterLink, Float reservationRate, Float grade, String story) { // 영화 검색
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.posterLink = posterLink;
        this.reservationRate = reservationRate;
        this.grade = grade;
        this.story = story;
    }

    public MovieDtoSimple(Long movieId, String movieTitle, String posterLink, Float reservationRate, Float grade) { // 영화 목록
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.posterLink = posterLink;
        this.reservationRate = reservationRate;
        this.grade = grade;
    }
}
