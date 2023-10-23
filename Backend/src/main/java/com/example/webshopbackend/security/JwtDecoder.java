package com.example.webshopbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties properties;

    /**
     * Decodes a JWT token by verifying its signature with the secret key specified in the application properties.
     *
     * @param token The JWT token to decode.
     * @return DecodedJWT representing the JWT's claim set after successful verification.
     */
    public DecodedJWT decode(String token) {
        // Step 1: Create a JWT verifier with the specified secret key
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                // Step 2: Build a JWT verifier for token verification
                .build()
                // Step 3: Verify the token and return a DecodedJWT
                .verify(token);
    }
}

