package com.example.webshopbackend.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")

public class JwtProperties {
    /**
     * Saving the secret key, which is used for issuing and verifying JWTs.
     * The secret key is defined in the application.properties file.
     */
    private String secretKey;
}
