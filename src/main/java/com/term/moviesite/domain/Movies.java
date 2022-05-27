package com.term.moviesite.domain;

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
    @Column(name="MOVIE_ID")
    private Long movieId;

    @OneToOne
    @JoinColumn(name="DIRECTOR")
    private Workers director;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MoviesWorkers> moviesWorkers = new ArrayList<MoviesWorkers>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Screens> screens = new ArrayList<Screens>();

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="OPEN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date openDate; // 개봉일

    @Enumerated(EnumType.STRING)
    @Column(name="GENRE", nullable = false)
    private Genre genre;

    @Column(name="RUNNING_TIME", nullable = false)
    private Long runningTime;

    @Column(name="RATE", nullable = false)
    private Float rate;

    @Column(name="RESERVATION_RATE", nullable = false)
    private Float reservationRate;

    public Movies(String name, Workers director, LocalDate openDate, Genre genre, Long runningTime) {
        this.name = name;
        this.director = director;
        this.openDate = LocalDateToDate.localDateToDate(openDate);
        this.genre = genre;
        this.runningTime = runningTime;
    }

    public void addScreen(Screens screen) {
        this.screens.add(screen);
        if(screen.getMovie() != this) {
            screen.setMovie(this);
        }
    }

    public void addMoviesWorkers(MoviesWorkers movieWorker) {
        this.moviesWorkers.add(movieWorker);
        if(movieWorker.getMovie() != this) {
            movieWorker.setMovie(this);
        }
    }
}
