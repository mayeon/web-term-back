package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TICKET_ID", nullable = false)
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SCREEN_ID", nullable = false)
    private Screens screen;

    @OneToMany(mappedBy = "ticket")
    private List<TicketsSeats> ticketsSeats = new ArrayList<TicketsSeats>();

    public Tickets(Users user, Screens screen) {
        setUser(user);
        this.screen = screen;
    }

    public void setUser(Users user) {
        if (this.user != null)
            this.user.getTickets().remove(this);
        this.user = user;
        user.getTickets().add(this);
    }
}
