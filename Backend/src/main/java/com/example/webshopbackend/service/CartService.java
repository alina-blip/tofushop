package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.model.Cart;

import java.util.List;

public interface CartService {
    CartDTO save(CartDTO cartDTO);
    List<CartDTO> findAll();
    CartDTO update(Long cartId, CartDTO cartDTO);
}
