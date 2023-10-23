package com.example.webshopbackend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

/**
 * Converts a decoded JWT into a UserPrincipal object with user data.
 *
 * @param jwt The decoded JWT to convert.
 * @return UserPrincipal object containing user data.
 */ public class JwtToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .email(jwt.getClaim("e").asString())
                .authorities(extractAuthoritysFromClaim(jwt))
                .build();
    }

    /**
     * Extracts user roles (authorities) from the JWT and stores them in a list of SimpleGrantedAuthority.
     *
     * @param jwt The decoded JWT containing user role claims.
     * @return List of SimpleGrantedAuthority representing user roles.
     */
    private List<SimpleGrantedAuthority> extractAuthoritysFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
