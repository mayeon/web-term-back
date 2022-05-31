package com.term.moviesite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDtoSimple {
    private String movieTitle;
    private String posterLink;
    private Float reservationRate;
    private Float grade;}
