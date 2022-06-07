package com.term.moviesite.domain;

import com.term.moviesite.domain.enums.Genre;
import com.term.moviesite.util.LocalDateToDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MOVIE_ID", nullable = false)
    private Long movieId;

    @Column(name="TITLE", nullable = false)
    private String title;

    @Column(name="Story", nullable = false)
    private String story;

    @Column(name="DIRECTOR", nullable = false)
    private String director;

    @Column(name="OPEN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date openDate; // 개봉일

    @Enumerated(EnumType.STRING)
    @Column(name="GENRE", nullable = false)
    private Genre genre;

    @Column(name="RUNNING_TIME", nullable = false)
    private Short runningTime;

    @Column(name="RESERVATION_RATE", nullable = false)
    private Float reservationRate;

    @Column(name="GRADE", nullable = false)
    private Float grade;

    @Column(name="POSTER_LINK", nullable = false)
    private String posterLink;

    @OneToMany(mappedBy = "movie")
    private List<Screens> screens = new ArrayList<Screens>();

    @OneToMany(mappedBy = "movie")
    private List<MoviesActors> moviesActors = new ArrayList<MoviesActors>();

    @OneToMany(mappedBy = "movie")
    private List<Reviews> reviews = new ArrayList<Reviews>();

    public Movies(String title, String director, String story, LocalDate openDate, Genre genre, Short runningTime, String posterLink) {
        this.title = title;
        this.director = director;
        this.story = story;
        this.openDate = LocalDateToDate.localDateToDate(openDate);
        this.genre = genre;
        this.runningTime = runningTime;
        this.reservationRate = 0f;
        this.grade = 0f;
        this.posterLink = posterLink;
    }
}
