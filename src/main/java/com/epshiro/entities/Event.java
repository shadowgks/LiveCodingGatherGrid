package com.epshiro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime date;
    private Integer max;
    private Double price;
    @ManyToOne
    private User user;

    public Event(String name, Integer max, LocalDateTime date, Double price, User user){
        this.name = name;
        this.max = max;
        this.date = date;
        this.price = price;
        this.user = user;
    }

    public Event() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getMax() {
        return max;
    }

    public Double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) && Objects.equals(date, event.date) && Objects.equals(max, event.max) && Objects.equals(price, event.price) && Objects.equals(user, event.user);
    }
}
