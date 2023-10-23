package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.CartDTO;
import java.util.List;
import java.util.Optional;

public interface CartService {

    /**
     * Save a Shopping Cart
     *
     * This method is responsible for creating or saving a new shopping cart using the provided `CartDTO`.
     *
     * @param cartDTO The CartDTO representing the shopping cart to be saved.
     * @return The CartDTO representing the saved shopping cart.
     */
    CartDTO save(CartDTO cartDTO);

    /**
     * Retrieve All Shopping Carts
     *
     * This method retrieves a list of all available shopping carts in the webshop.
     *
     * @return A list of CartDTO objects representing the available shopping carts.
     */
    List<CartDTO> findAll();

    /**
     * Update a Shopping Cart
     *
     * This method is used to update an existing shopping cart identified by `cartId` with the details provided in `cartDTO`.
     *
     * @param cartId  The unique identifier of the shopping cart to be updated.
     * @param cartDTO The CartDTO containing the updated information for the shopping cart.
     * @return The CartDTO representing the updated shopping cart.
     */
    CartDTO update(Long cartId, CartDTO cartDTO);

    /**
     * Find a Shopping Cart by ID
     *
     * This method is used to find a shopping cart by its unique identifier.
     *
     * @param id The unique identifier (typically an ID) of the shopping cart to be found.
     * @return An Optional containing the CartDTO if found, or an empty Optional if no matching cart is found.
     */
    Optional<CartDTO> findById(long id);
}
