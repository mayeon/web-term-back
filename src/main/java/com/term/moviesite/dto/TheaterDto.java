package com.term.moviesite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDto {
    private String theaterName;
    private Short theaterFloor;
    private Character row;
    private Short col;
}
