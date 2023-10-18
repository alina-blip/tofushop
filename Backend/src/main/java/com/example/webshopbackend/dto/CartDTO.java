package com.example.webshopbackend.dto;

import com.example.webshopbackend.model.Original;

import java.time.LocalDate;
import java.util.List;

public class CartDTO {

    private long cart_id;
    private long userId; // Instead of User object, userId is stored - its better
    private List<Original> originals; // List of products
    private LocalDate date;


    public CartDTO(long cart_id, long userId, List<Original> originals, LocalDate date) {
        this.cart_id = cart_id;
        this.userId = userId;
        this.originals = originals;
        this.date = date;
    }

    public CartDTO() {

    }


    public long getCart_id() {
        return cart_id;
    }

    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
