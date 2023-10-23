/**
 * Cart Class
 *
 * This class represents a cart in the webshop, which holds a collection of original products, associated with a user and a creation date.
 */
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
    private User user; // The user associated with the cart

    @ManyToMany
    private List<Original> originals; // List of original products in the cart

    private LocalDate date; // The date when the cart was created

    /**
     * Get the cart's unique identifier.
     *
     * @return The cart_id, which is a unique identifier for the cart.
     */
    public long getCart_id() {
        return cart_id;
    }

    /**
     * Set the cart's unique identifier.
     *
     * @param cart_id The unique identifier to set for the cart.
     */
    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    /**
     * Get the user associated with the cart.
     *
     * @return The user object associated with the cart.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user associated with the cart.
     *
     * @param user The user object to associate with the cart.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the list of original products in the cart.
     *
     * @return A list of original products in the cart.
     */
    public List<Original> getOriginals() {
        return originals;
    }

    /**
     * Set the list of original products in the cart.
     *
     * @param originals A list of original products to set in the cart.
     */
    public void setOriginals(List<Original> originals) {
        this.originals = originals;
    }

    /**
     * Get the creation date of the cart.
     *
     * @return The creation date of the cart, represented as a LocalDate.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the creation date of the cart.
     *
     * @param date The creation date to set for the cart, represented as a LocalDate.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
