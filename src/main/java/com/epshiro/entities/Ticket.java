package com.epshiro.entities;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @ManyToOne
    private Event event;
    @ManyToOne
    private User user;


    public Ticket(Integer number, Event event, User user) {
        this.number = number;
        this.event = event;
        this.user = user;
    }

    public Ticket() {

    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public Event getEvent() {
        return event;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                ", event=" + event +
                ", user=" + user +
                '}';
    }
}
