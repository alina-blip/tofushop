package com.example.webshopbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties properties;

    /**
     * Generates a JSON Web Token (JWT) with specific claims.
     *
     * @param userId The user's ID to be included in the token.
     * @param email The user's email address to be included in the token.
     * @param roles The user's roles to be included in the token.
     * @return A JWT as a string.
     */
    public String issue(long userId, String email, List<String> roles) {
        // Step 1: Create a JWT with various settings and claims
        return JWT.create()
                // Step 2: Set the subject (sub) claim to the user's ID
                .withSubject(String.valueOf(userId))
                // Step 3: Set the token expiration time to 1 day from the current time
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                // Step 4: Set the 'e' claim to the user's email address
                .withClaim("e", email)
                // Step 5: Set the 'a' claim to the user's roles
                .withClaim("a", roles)
                // Step 6: Sign the JWT using HMAC256 with the secret key and return it as a string
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
