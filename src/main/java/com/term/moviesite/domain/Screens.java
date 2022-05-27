package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Screens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SCREEN_ID")
    private Long screenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID")
    private Movies movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID")
    private Theaters theater;

    @OneToMany(mappedBy = "screen", fetch = FetchType.LAZY)
    private List<Seats> seats = new ArrayList<Seats>();

    @Column(name="START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name="END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Screens(Movies movie, Theaters theater, LocalDateTime startTime) {
        setMovie(movie);
        setTheater(theater);
        this.startTime = java.sql.Timestamp.valueOf(startTime);
        this.endTime = calcTime(startTime);
    }

    public void setTheater(Theaters theater) {
        if (this.theater != null)
            this.theater.getScreens().remove(this);
        this.theater = theater;
        theater.getScreens().add(this);
    }

    public void setMovie(Movies movie) {
        if (this.movie != null)
            this.movie.getScreens().remove(this);
        this.movie = movie;
        movie.getScreens().add(this);
    }

    public Date calcTime(LocalDateTime startTime) {
        int hour = startTime.getHour();
        int minute = startTime.getMinute() + movie.getRunningTime().intValue();
        while (minute > 59) {
            minute -= 60;
            hour += 1;
        }
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(),
                                                 hour, minute, 0);
        return java.sql.Timestamp.valueOf(endTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("영화 이름: " + movie.getName() + "\n");
        sb.append("상영관: " + theater.getTheaterName() + "\n");
        sb.append("시작 시간: " + startTime + "\n");
        sb.append("종료 시간: " + endTime + "\n");
        sb.append("총 좌석: " + seats.size() + "\n");
        sb.append("가능 좌석: ");

        int availableSeatCount = 10;
        for (Seats seat: seats) {
            if (seat.getIsReserved())
                availableSeatCount--;
        }
        sb.append(availableSeatCount + "\n");

        sb.append("좌석 현황: \n");
        int seatsCount = 0;
        for (Seats seat: seats) {
            if (seat.getIsReserved())
                sb.append("■ ");
            else
                sb.append("□ ");

            if (seatsCount == 4)
                sb.append("\n");
            seatsCount++;
        }
        sb.append("\n");

        return sb.toString();
    }
}
