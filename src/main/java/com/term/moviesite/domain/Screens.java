package com.term.moviesite.domain;

import com.term.moviesite.domain.enums.DiscountPolicy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Screens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SCREEN_ID", nullable = false)
    private Long screenId;

    @Column(name="START_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name="END_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name="PRICE", nullable = false)
    private Short price;

    @Enumerated(EnumType.STRING)
    @Column(name="DISCOUNT_POLICY", nullable = false)
    private DiscountPolicy discountPolicy;

    @Column(name="DISCOUNT_RATE", nullable = false)
    private Short discountRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID", nullable = false)
    private Movies movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID", nullable = false)
    private Theaters theater;

    @OneToMany(mappedBy = "screen")
    private List<Seats> seats = new ArrayList<Seats>();

    @OneToMany(mappedBy = "screen")
    private List<Tickets> tickets = new ArrayList<Tickets>();

    public Screens(Movies movie, Theaters theater, LocalDateTime startTime, Short price, DiscountPolicy discountPolicy, Short discountRate) {
        setMovie(movie);
        setTheater(theater);
        this.startTime = java.sql.Timestamp.valueOf(startTime);
        this.price = price;
        this.endTime = calcTime(startTime);
        this.discountPolicy = discountPolicy;
        this.discountRate = discountRate;
        this.movie = movie;
        this.theater = theater;
    }

    public short getPrice() {
        if (discountPolicy == DiscountPolicy.FIXED_AMOUNT) {
            return (short)(price - discountRate);
        } else {
            return (short)(price * (1 - discountRate * 0.01));
        }
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void setDiscountRate(short discountRate) {
        this.discountRate = discountRate;
    }

    public void setMovie(Movies movie) {
        if (this.movie != null)
            this.movie.getScreens().remove(this);
        this.movie = movie;
        movie.getScreens().add(this);
    }

    public void setTheater(Theaters theater) {
        if (this.theater != null)
            this.theater.getScreens().remove(this);
        this.theater = theater;
        theater.getScreens().add(this);
    }

    public Date calcTime(LocalDateTime startTime) {
        int hour = startTime.getHour();
        int minute = startTime.getMinute() + movie.getRunningTime().intValue();
        while (minute > 59) {
            minute -= 60;
            hour += 1;
        }
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(),
                                                 hour, minute, 0);
        return java.sql.Timestamp.valueOf(endTime);
    }
}
