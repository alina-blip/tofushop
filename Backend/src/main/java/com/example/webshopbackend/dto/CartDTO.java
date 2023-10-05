package com.example.webshopbackend.dto;

import java.time.LocalDate;

public class CartDTO {

    private long id;
    private long userId; // Instead of User object, userId is stored - its better
    private long originalId; // Instead of Original object - originalId
    private int count;
    private LocalDate date;

    // Constructors, getters, and setters

    public CartDTO() {
    } //constructor empty

    public CartDTO(long id, long userId, long originalId, String category, String size, int count, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.originalId = originalId;
        this.count = count;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(long originalId) {
        this.originalId = originalId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
