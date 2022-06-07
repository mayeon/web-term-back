package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LIKE_ID", nullable = false)
    private Long LikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID", nullable = false)
    private Reviews review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users user;

    public Likes(Reviews review, Users user) {
        setReviewId(review);
        setUserId(user);
    }

    public void setReviewId(Reviews review) {
        if (this.review != null)
            this.review.getLikes().remove(this);
        this.review = review;
        review.getLikes().add(this);
    }

    public void setUserId(Users user) {
        if (this.user != null)
            this.user.getLikes().remove(this);
        this.user = user;
        user.getLikes().add(this);
    }

}
