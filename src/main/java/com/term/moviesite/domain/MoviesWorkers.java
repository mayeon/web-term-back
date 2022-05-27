package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="MOVIES_WORKERS")
@Getter
@NoArgsConstructor
public class MoviesWorkers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MOVIES_WORKERS_ID")
    private Long moviesWorkersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movies movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKER_ID")
    private Workers worker;

    public MoviesWorkers(Movies movie, Workers worker) {
        setMovie(movie);
        setWorker(worker);
    }

    public void setMovie(Movies movie) {
        if (this.movie != null)
            this.movie.getMoviesWorkers().remove(this);
        this.movie = movie;
        movie.getMoviesWorkers().add(this);
    }

    public void setWorker(Workers worker) {
        if (this.worker != null)
            this.worker.getMoviesWorkers().remove(this);
        this.worker = worker;
        worker.getMoviesWorkers().add(this);
    }
}
