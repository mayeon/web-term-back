package com.term.moviesite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MovieScreenDto {
    private Long movieId;
    private String movieName;
    private List<ScreenDto> screens;
}
