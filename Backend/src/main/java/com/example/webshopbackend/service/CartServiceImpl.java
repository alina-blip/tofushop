package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.model.Cart;
import com.example.webshopbackend.model.Original;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    @Autowired
    public CartServiceImpl(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartDTO save(CartDTO cartDTO) {
        Cart cart = convertToEntity(cartDTO);
        Cart savedCart = repository.save(cart);
        return convertToDTO(savedCart);
    }

    public List<CartDTO> findAll() {
        List<Cart> cartList = repository.findAll();
        return cartList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setId(cartDTO.getUserId());
        cart.setId(cartDTO.getOriginalId());
        cart.setCount(cartDTO.getCount());
        cart.setDate(cartDTO.getDate());
        return cart;
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getId());
        cartDTO.setOriginalId(cart.getId());
        cartDTO.setCount(cart.getCount());
        cartDTO.setDate(cart.getDate());
        return cartDTO;
    }

}
