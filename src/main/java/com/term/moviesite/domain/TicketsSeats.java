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
    @Column(name="TICKETS_SEATS_ID", nullable = false)
    private Long ticketsSeatsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Tickets ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID", nullable = false)
    private Seats seat;

    public TicketsSeats(Tickets ticket, Seats seat) {
        setTicket(ticket);
        setSeat(seat);
    }

    public void setTicket(Tickets ticket) {
        if (this.ticket != null)
            this.ticket.getTicketsSeats().remove(this);
        this.ticket = ticket;
        ticket.getTicketsSeats().add(this);
    }

    public void setSeat(Seats seat) {
        if (this.seat != null)
            this.seat.getTicketsSeats().remove(this);
        this.seat = seat;
        seat.getTicketsSeats().add(this);
    }
}
