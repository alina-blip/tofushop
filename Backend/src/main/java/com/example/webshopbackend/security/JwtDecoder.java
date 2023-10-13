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

    public DecodedJWT decode(String token){
    return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))//token wird mit HMAC256 verifiziert
        .build() //erstellt einen JWT-Verifierfür die Verifizierung des Tokens
        .verify(token); // gibt ein DecodedJWT zurück
    }
}
