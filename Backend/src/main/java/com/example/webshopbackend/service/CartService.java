package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    CartDTO save(CartDTO cartDTO);
    List<CartDTO> findAll();
    CartDTO update(Long cartId, CartDTO cartDTO);
    Optional<CartDTO> findById(long id);
}
