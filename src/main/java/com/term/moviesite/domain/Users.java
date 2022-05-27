package com.term.moviesite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Tickets> tickets = new ArrayList<Tickets>();

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="AGE", nullable = false)
    private Integer age;

    public Users(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void addTicket(Tickets ticket) {
        this.tickets.add(ticket);
        if(ticket.getUser() != this) {
            ticket.setUser(this);
        }
    }
}
