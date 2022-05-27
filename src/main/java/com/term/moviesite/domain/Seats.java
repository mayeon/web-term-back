package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SEAT_ID")
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID")
    private Theaters theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SCREEN_ID")
    private Screens screen;

    @Column(name="MATRIX_ROW", nullable = false)
    private Integer row;

    @Column(name="MATRIX_COL", nullable = false)
    private Integer col;

    @Column(name="IS_RESERVED")
    private Boolean isReserved;

    public Seats(Theaters theater, Screens screen, Integer row, Integer col) {
        setTheater(theater);
        setTScreen(screen);
        this.row = row;
        this.col = col;
        this.isReserved = false;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public void setTheater(Theaters theater) {
        if (this.theater != null)
            this.theater.getSeats().remove(this);
        this.theater = theater;
        theater.getSeats().add(this);
    }

    public void setTScreen(Screens screen) {
        if (this.screen != null)
            this.screen.getSeats().remove(this);
        this.screen = screen;
        screen.getSeats().add(this);
    }
}
