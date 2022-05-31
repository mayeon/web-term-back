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
    @Column(name="SEAT_ID", nullable = false)
    private Long seatId;

    @Column(name="MATRIX_ROW", nullable = false)
    private Character row;

    @Column(name="MATRIX_COL", nullable = false)
    private Short col;

    @Column(name="IS_RESERVED")
    private Boolean isReserved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID", nullable = false)
    private Theaters theater;

    @OneToMany(mappedBy = "seat")
    private List<TicketsSeats> ticketsSeats = new ArrayList<TicketsSeats>();

    public Seats(Theaters theater, Character row, Short col) {
        setTheater(theater);
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
}
