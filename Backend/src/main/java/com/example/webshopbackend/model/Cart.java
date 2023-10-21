package com.example.webshopbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private long cart_id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Original> originals;

    private LocalDate date;


    public long getCart_id() {
        return cart_id;
    }

    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Original> getOriginals() {
        return originals;
    }

    public void setOriginals(List<Original> originals) {
        this.originals = originals;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}




