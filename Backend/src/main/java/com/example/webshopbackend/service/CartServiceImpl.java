package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.dto.OriginalDTO;
import com.example.webshopbackend.exception.StorageException;
import com.example.webshopbackend.model.Cart;
import com.example.webshopbackend.model.Original;
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
            // Handle the case where the cart with the given cartId doesn't exist
            throw new StorageException("Cart not found.");
        }
    }

    @Override
    public Optional<CartDTO> findById(long id) {
        Optional<Cart> cart = repository.findById(id);
        return cart.map(this::convertToDTO);
    }

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



    public List<CartDTO> findAll() {
        List<Cart> cartList = repository.findAll();
        return cartList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

     Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setCart_id(cartDTO.getCart_id());
        cart.setOriginals(cartDTO.getOriginals());
        cart.setDate(cartDTO.getDate());
        return cart;
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(cart.getCart_id());
        cartDTO.setUserId(cart.getUser().getId()); // Set the user ID
        cartDTO.setOriginals(cart.getOriginals());
        cartDTO.setDate(cart.getDate());
        return cartDTO;
    }

}
