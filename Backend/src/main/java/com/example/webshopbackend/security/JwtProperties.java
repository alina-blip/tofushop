package com.example.webshopbackend.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
//speichert den secret key der für die sicherung und überprüfung von JWTs verwendet wird (in application.properties)
public class JwtProperties {
    /**
     * Secret key used for issuing JWT
     */
    private String secretKey;
}
