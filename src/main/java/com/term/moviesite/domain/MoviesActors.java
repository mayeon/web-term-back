package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="MOVIES_ACTORS")
@Getter
@NoArgsConstructor
public class MoviesActors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MOVIES_ACTORS_ID")
    private Long moviesActorsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    private Movies movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTOR_ID", nullable = false)
    private Actors actor;

    public MoviesActors(Movies movie, Actors actor) {
        setMovie(movie);
        setActor(actor);
    }

    public void setMovie(Movies movie) {
        if (this.movie != null)
            this.movie.getMoviesActors().remove(this);
        this.movie = movie;
        movie.getMoviesActors().add(this);
    }

    public void setActor(Actors actor) {
        if (this.actor != null)
            this.actor.getMoviesWorkers().remove(this);
        this.actor = actor;
        actor.getMoviesWorkers().add(this);
    }
}
