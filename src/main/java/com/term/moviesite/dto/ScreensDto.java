package com.term.moviesite.dto;

import com.term.moviesite.domain.Theaters;
import com.term.moviesite.domain.enums.Genre;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScreensDto {
    //(제목, 장르, 런타임, 개봉일, 상영관 정보, 상영 시간)
    private String title;
    private Genre genre;
    private Date openDate;
    private Short runningTime;
    private Theaters theater;
    private Date startTime;
}
