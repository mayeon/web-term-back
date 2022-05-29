package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="REVIEW_ID", nullable = false)
    private Long reviewId;

    @Column(name="COMMENT", nullable = false)
    private String comment;

    @Column(name="LIKE", nullable = false)
    private Integer like;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID", nullable = false)
    private Movies movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable = false)
    private Users user;

    public Reviews(Users user, Movies movie, String comment, Integer like) {
        setUser(user);
        setMovie(movie);
        this.comment = comment;
        this.like = 0;
    }

    public void setMovie(Movies movie) {
        if (this.movie != null)
            this.movie.getReviews().remove(this);
        this.movie = movie;
        user.getReviews().add(this);
    }

    public void setUser(Users user) {
        if (this.user != null)
            this.user.getReviews().remove(this);
        this.user = user;
        user.getReviews().add(this);
    }
}
