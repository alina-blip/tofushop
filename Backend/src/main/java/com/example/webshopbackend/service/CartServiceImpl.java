package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.exception.StorageException;
import com.example.webshopbackend.model.Cart;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.repository.CartRepository;
import com.example.webshopbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public CartServiceImpl(CartRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /**
     * Update a Shopping Cart
     *
     * This method updates an existing shopping cart with the provided data from the CartDTO.
     *
     * @param cart_id  The unique identifier of the shopping cart to be updated.
     * @param cartDTO The CartDTO containing the updated information for the shopping cart.
     * @return The CartDTO representing the updated shopping cart.
     * @throws StorageException if the cart with the given cart_id is not found.
     */
    @Override
    public CartDTO update(Long cart_id, CartDTO cartDTO) {
        Optional<Cart> cartOptional = repository.findById(cart_id);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            // Update the fields of the cart with the data from cartDTO
            cart.setOriginals(cartDTO.getOriginals());

            // You can add additional logic here if needed

            // Save the updated cart
            Cart updatedCart = repository.save(cart);
            return convertToDTO(updatedCart);
        } else {
            // Handle the case where the cart with the given cart_id doesn't exist
            throw new StorageException("Cart not found.");
        }
    }

    /**
     * Find a Shopping Cart by ID
     *
     * This method is used to find a shopping cart by its unique identifier.
     *
     * @param id The unique identifier (typically an ID) of the shopping cart to be found.
     * @return An Optional containing the CartDTO if found, or an empty Optional if no matching cart is found.
     */
    @Override
    public Optional<CartDTO> findById(long id) {
        Optional<Cart> cart = repository.findById(id);
        return cart.map(this::convertToDTO);
    }

    /**
     * Save a Shopping Cart
     *
     * This method is responsible for creating or saving a new shopping cart using the provided CartDTO.
     *
     * @param cartDTO The CartDTO representing the shopping cart to be saved.
     * @return The CartDTO representing the saved shopping cart.
     * @throws StorageException if the user with the provided userId is not found.
     */
    @Override
    public CartDTO save(CartDTO cartDTO) {
        // Check if the user with the provided userId exists in your user database
        Optional<User> userOptional = userRepository.findById(cartDTO.getUserId());

        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Extract the User object from Optional
            Cart cart = convertToEntity(cartDTO);
            cart.setUser(user); // Set the user for the cart
            Cart savedCart = repository.save(cart);
            return convertToDTO(savedCart);
        } else {
            // Handle the case where the user doesn't exist
            throw new StorageException("User not found.");
        }
    }

    /**
     * Find all Shopping Carts
     *
     * This method retrieves a list of all available shopping carts in the webshop.
     *
     * @return A list of CartDTO objects representing the available shopping carts.
     */
    public List<CartDTO> findAll() {
        List<Cart> cartList = repository.findAll();
        return cartList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Convert CartDTO to Cart Entity
     *
     * This method converts a CartDTO to a Cart entity for database storage.
     *
     * @param cartDTO The CartDTO to be converted.
     * @return The Cart entity representing the cart.
     */
    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setCart_id(cartDTO.getCart_id());
        cart.setOriginals(cartDTO.getOriginals());
        cart.setDate(cartDTO.getDate());
        return cart;
    }

    /**
     * Convert Cart Entity to CartDTO
     *
     * This method converts a Cart entity to a CartDTO for API response.
     *
     * @param cart The Cart entity to be converted.
     * @return The CartDTO representing the cart.
     */
    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(cart.getCart_id());
        cartDTO.setUserId(cart.getUser().getId()); // Set the user ID
        cartDTO.setOriginals(cart.getOriginals());
        cartDTO.setDate(cart.getDate());
        return cartDTO;
    }
}
