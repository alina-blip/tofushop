package com.example.webshopbackend.model;

/**
 * UserRole Enum
 *
 * This enum represents the roles that users can have in the webshop system. Users can have one of the following roles: ADMIN, USER, or ANONYM (anonymous).
 */
public enum UserRole {
    ADMIN, // Represents an administrator with elevated privileges
    USER,  // Represents a regular user
    ANONYM // Represents an anonymous user with limited privileges
}

