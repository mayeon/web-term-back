package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="TICKETS_SEATS")
@Getter
@NoArgsConstructor
public class TicketsSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TICKETS_SEATS_ID")
    private Long ticketSeatsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")
    private Tickets ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID")
    private Seats seat;

    public TicketsSeats(Tickets ticket, Seats seat) {
        setTicket(ticket);
        this.seat = seat;
    }

    public void setTicket(Tickets ticket) {
        if (this.ticket != null)
            this.ticket.getTicketsSeats().remove(this);
        this.ticket = ticket;
        ticket.getTicketsSeats().add(this);
    }
}
