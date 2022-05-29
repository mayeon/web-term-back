package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Actors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACTOR_ID")
    private Long actorId;

    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "actor")
    private List<MoviesActors> moviesWorkers = new ArrayList<MoviesActors>();

    public Actors(String name) {
        this.name = name;
    }
}
