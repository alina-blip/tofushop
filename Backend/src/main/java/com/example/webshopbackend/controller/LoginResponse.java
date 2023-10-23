/**
 * LoginResponse Class
 *
 * This class represents a response object returned after a successful user login. It contains an access token that can
 * be used for authentication and authorization.
 */
package com.example.webshopbackend.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    /**
     * The access token generated upon successful user login.
     */
    private final String accessToken;
}
