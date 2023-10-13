package com.example.webshopbackend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

//nimmt ein decodiertes Jwt und konventiert es in ein UserPrincipal Objekt mit benutzerdaten um
public class JwtToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt) {
     return UserPrincipal.builder()
             .userId(Long.valueOf(jwt.getSubject()))
             .email(jwt.getClaim("e").asString())
             .authorities(extractAuthoritysFromClaim(jwt))
             .build();
}
    // benutzerrollen werden aus dem JWT extrahiert und in eine Liste (SimpleGrantedAuthority) gespeichert
private List<SimpleGrantedAuthority> extractAuthoritysFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim ("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
