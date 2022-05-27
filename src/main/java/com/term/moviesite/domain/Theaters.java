package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Theaters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="THEATER_ID")
    private Long theaterId;

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private List<Seats> seats = new ArrayList<Seats>();

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private List<Screens> screens = new ArrayList<Screens>();

    @Column(name="THEATER_NAME", nullable = false)
    private String theaterName;

    @Column(name="FLOOR", nullable = false)
    private Integer floor;

    public Theaters(String theaterName, Integer floor) {
        this.theaterName = theaterName;
        this.floor = floor;
    }

    public void addSeat(Seats seat) {
        this.seats.add(seat);
        if(seat.getTheater() != this) {
            seat.setTheater(this);
        }
    }

    public void addScreen(Screens screen) {
        this.screens.add(screen);
        if(screen.getTheater() != this) {
            screen.setTheater(this);
        }
    }
}
