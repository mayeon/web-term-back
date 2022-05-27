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
    @Column(name="TICKET_ID")
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SCREEN_ID")
    private Screens screen;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TicketsSeats> ticketsSeats = new ArrayList<TicketsSeats>();

//    @ManyToMany
//    @JoinTable(name="TICKETS_SEATS",
//            joinColumns = @JoinColumn(name="TICKET_ID"),
//            inverseJoinColumns = @JoinColumn(name="SEAT_ID"))
//    private List<Seats> seats = new ArrayList<Seats>();

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
