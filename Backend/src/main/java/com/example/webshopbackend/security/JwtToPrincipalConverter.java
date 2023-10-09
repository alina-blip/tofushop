package com.example.webshopbackend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt) {
     return UserPrincipal.builder()
             .userId(Long.valueOf(jwt.getSubject()))
             .email(jwt.getClaim("e").asString())
             .authorities(extractAuthoritysFromClaim(jwt))
             .build();
}

private List<SimpleGrantedAuthority> extractAuthoritysFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim ("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
