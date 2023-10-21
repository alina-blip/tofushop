package com.example.webshopbackend.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class OriginalTest {

    @Test
    public void testOriginalTitle() {
        Original original = new Original();
        original.setTitle("Beautiful Landscape");

        assertEquals("Beautiful Landscape", original.getTitle());
    }

    @Test
    public void testOriginalPrice() {
        Original original = new Original();
        original.setPrice(99.99f);

        assertEquals(99.99f, original.getPrice(), 0.01); // Use delta for floating-point comparison
    }
    @Test
    public void testOriginalDescription() {
        Original original = new Original();
        original.setDescription("A stunning painting of a serene forest.");

        assertEquals("A stunning painting of a serene forest.", original.getDescription());
    }

}
