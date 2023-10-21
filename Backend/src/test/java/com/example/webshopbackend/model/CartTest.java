package com.example.webshopbackend.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testCartUser() {
        Cart cart = new Cart();
        User user = new User(); // Create a user object
        cart.setUser(user);

        assertEquals(user, cart.getUser());
    }

    @Test
    public void testCartOriginals() {
        Cart cart = new Cart();
        Original original1 = new Original(); // Create an original object
        Original original2 = new Original(); // Create another original object
        List<Original> originals = Arrays.asList(original1, original2);
        cart.setOriginals(originals);

        assertEquals(originals, cart.getOriginals());
    }
    @Test
    public void testCartDate() {
        Cart cart = new Cart();
        LocalDate date = LocalDate.now();
        cart.setDate(date);

        assertEquals(date, cart.getDate());
    }

}
