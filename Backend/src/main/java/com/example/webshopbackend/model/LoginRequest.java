package com.example.webshopbackend.model;

import lombok.Getter;
import lombok.Setter;
/**
 * LoginRequest Class
 *
 * This class represents a request for user authentication, containing the user's email and password.
 */
@Getter
@Setter
public class LoginRequest {

    private String email;    // The user's email for authentication
    private String password; // The user's password for authentication
}

