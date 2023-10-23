package com.example.webshopbackend.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class OriginalTest {

    @Test
    public void testOriginalTitle() {
        Original original = new Original();
        original.setTitle("Beautiful Landscape");
        // Ensure that the title of the Original matches the one set

        assertEquals("Beautiful Landscape", original.getTitle());
    }

    @Test
    public void testOriginalPrice() {
        Original original = new Original();
        original.setPrice(99.99f);
        // Ensure that the price of the Original matches the one set, using a delta for floating-point comparison

        assertEquals(99.99f, original.getPrice(), 0.01); // Use delta for floating-point comparison
    }
    @Test
    public void testOriginalDescription() {
        Original original = new Original();
        original.setDescription("A stunning painting of a serene forest.");
        // Ensure that the description of the Original matches the one set

        assertEquals("A stunning painting of a serene forest.", original.getDescription());
    }

}
