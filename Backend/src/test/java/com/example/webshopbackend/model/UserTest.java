package com.example.webshopbackend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSurname("Doe");
        user.setStreet("123 Main St");
        user.setHousenumber("A1");
        user.setPostalcode("12345");
        user.setCountry("Example Country");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePassword");
        user.setRole(UserRole.USER);

        //  check properties of the user
        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals("123 Main St", user.getStreet());
        assertEquals("A1", user.getHousenumber());
        assertEquals("12345", user.getPostalcode());
        assertEquals("Example Country", user.getCountry());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("securePassword", user.getPassword());
        assertEquals(UserRole.USER, user.getRole());
    }


    @Test
    public void testUserEmailNotEqual() {
        User user1 = new User();
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setEmail("john.doe@example.com");

        User user3 = new User();
        user3.setEmail("jane.smith@example.com");

        assertNotEquals(user1, user2); // user1 and user2 should not be equal
        assertNotEquals(user1, user3); // user1 and user3 should not be equal
        assertNotEquals(user2, user3); // user2 and user3 should not be equal
    }

    @Test
    public void testUserEmail() {
        User user = new User();
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
    }

    @Test
    public void testUserRole() {
        User user = new User();
        user.setRole(UserRole.USER);
        //assert the right user role
        assertEquals(UserRole.USER, user.getRole());
    }
}
