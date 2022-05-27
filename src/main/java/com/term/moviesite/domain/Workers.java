package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WORKER_ID")
    private Long workerId;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
    private List<MoviesWorkers> moviesWorkers = new ArrayList<MoviesWorkers>();

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="TYPE", nullable = false)
    private Character type;

    public Workers(String name, Character type) {
        this.name = name;
        this.type = type;
    }

    public void addMoviesWorkers(MoviesWorkers movieWorker) {
        this.moviesWorkers.add(movieWorker);
        if(movieWorker.getWorker() != this) {
            movieWorker.setWorker(this);
        }
    }
}
