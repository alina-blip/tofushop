/**
 * CartDTO Class
 *
 * This class represents a Data Transfer Object (DTO) for managing user shopping carts. It includes information about the
 * cart's unique identifier, the associated user, the list of original products in the cart, and the cart's creation date.
 */
package com.example.webshopbackend.dto;

import com.example.webshopbackend.model.Original;

import java.time.LocalDate;
import java.util.List;

public class CartDTO {

    /**
     * The unique identifier of the shopping cart.
     */
    private long cart_id;

    /**
     * The user's unique identifier associated with the cart.
     */
    private long userId;

    /**
     * A list of original products included in the cart.
     */
    private List<Original> originals;

    /**
     * The date when the cart was created.
     */
    private LocalDate date;

    /**
     * Constructs an empty CartDTO.
     */
    public CartDTO() {}

    /**
     * Constructs a CartDTO with specified attributes.
     *
     * @param cart_id The unique identifier of the cart.
     * @param userId The unique identifier of the associated user.
     * @param originals The list of original products in the cart.
     * @param date The creation date of the cart.
     */
    public CartDTO(long cart_id, long userId, List<Original> originals, LocalDate date) {
        this.cart_id = cart_id;
        this.userId = userId;
        this.originals = originals;
        this.date = date;
    }

    /**
     * Get the cart's unique identifier.
     *
     * @return The cart's unique identifier.
     */
    public long getCart_id() {
        return cart_id;
    }

    /**
     * Set the cart's unique identifier.
     *
     * @param cart_id The cart's unique identifier to set.
     */
    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    /**
     * Get the user's unique identifier associated with the cart.
     *
     * @return The user's unique identifier.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Set the user's unique identifier associated with the cart.
     *
     * @param userId The user's unique identifier to set.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Get the list of original products in the cart.
     *
     * @return The list of original products.
     */
    public List<Original> getOriginals() {
        return originals;
    }

    /**
     * Set the list of original products in the cart.
     *
     * @param originals The list of original products to set.
     */
    public void setOriginals(List<Original> originals) {
        this.originals = originals;
    }

    /**
     * Get the creation date of the cart.
     *
     * @return The creation date of the cart.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the creation date of the cart.
     *
     * @param date The creation date to set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
