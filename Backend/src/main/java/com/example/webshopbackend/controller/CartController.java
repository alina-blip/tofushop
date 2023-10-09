package com.example.webshopbackend.controller;
import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("")
    public CartDTO save(@RequestBody CartDTO cartDTO) {
        return service.save(cartDTO);
    }
    @GetMapping("")
    public List<CartDTO> all() {
        return service.findAll();
    }
}
