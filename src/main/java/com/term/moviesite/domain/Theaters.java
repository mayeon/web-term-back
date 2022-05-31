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
    @Column(name="THEATER_ID", nullable = false)
    private Long theaterId;

    @Column(name="THEATER_NAME", nullable = false)
    private String theaterName;

    @Column(name="FLOOR", nullable = false)
    private Short floor;

    @Column(name="MAX_ROW", nullable = false)
    private Character maxRow;

    @Column(name="MAX_COL", nullable = false)
    private Short maxCol;

    @OneToMany(mappedBy = "theater")
    private List<Seats> seats = new ArrayList<Seats>();

    @OneToMany(mappedBy = "theater")
    private List<Screens> screens = new ArrayList<Screens>();

    public Theaters(String theaterName, Short floor, Character maxRow, short maxCol) {
        this.theaterName = theaterName;
        this.floor = floor;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }
}
