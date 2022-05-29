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
    @Column(name="WORKER_ID")
    private Long workerId;

    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "worker")
    private List<MoviesActors> moviesWorkers = new ArrayList<MoviesActors>();

    public Actors(String name) {
        this.name = name;
    }
}
