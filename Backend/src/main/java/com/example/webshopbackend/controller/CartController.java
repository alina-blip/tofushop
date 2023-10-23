/**
 * CartController Class
 *
 * This class serves as the controller for managing shopping carts in the web application. It provides endpoints for
 * saving, updating, and retrieving cart information.
 */
package com.example.webshopbackend.controller;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    /**
     * Save a new cart.
     *
     * @param cartDTO The CartDTO containing cart information to be saved.
     * @return The saved CartDTO.
     */
    @PostMapping("")
    public CartDTO save(@RequestBody CartDTO cartDTO) {
        return service.save(cartDTO);
    }

    /**
     * Update an existing cart.
     *
     * @param cart_id The unique identifier of the cart to be updated.
     * @param cartDTO The CartDTO containing updated cart information.
     * @return The updated CartDTO.
     */
    @PutMapping("/{cart_id}")
    public CartDTO update(@PathVariable Long cart_id, @RequestBody CartDTO cartDTO) {
        return service.update(cart_id, cartDTO); // Implement the service method for updating
    }

    /**
     * Retrieve a list of all shopping carts.
     *
     * @return List of CartDTOs representing all shopping carts.
     */
    @GetMapping("")
    public List<CartDTO> all() {
        return service.findAll();
    }

    /**
     * Retrieve a specific shopping cart by its unique ID.
     *
     * @param id The unique identifier of the cart to retrieve.
     * @return An Optional containing the CartDTO if found, or an empty Optional if not found.
     */
    @GetMapping("/{id}")
    public Optional<CartDTO> one(@PathVariable long id) {
        return service.findById(id);
    }
}
