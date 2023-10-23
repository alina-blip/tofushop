package com.example.webshopbackend.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;

    private final JwtToPrincipalConverter jwtToPrincipalConverter;

    /**
     * This method is called when processing an HTTP request. It extracts the JWT token from the request header,
     * decodes it, and sets the authentication in the security context.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Step 1: Extract the JWT token from the request
        extractTokenFromRequest(request)
                // Step 2: Decode the JWT token into a claim set (possibly verifying the signature)
                .map(jwtDecoder::decode)
                // Step 3: Convert the JWT claim set into a user principal
                .map(jwtToPrincipalConverter::convert)
                // Step 4: Construct a UserPrincipalAuthenticationToken with the user principal
                .map(UserPrincipalAuthenticationToken::new)
                // Step 5: Set the authentication in the SecurityContextHolder
                .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));

        // Continue processing the request
        filterChain.doFilter(request, response);
    }

    /**
     * This method is called to extract the JWT token from the request header.
     * It checks the "Authorization" header and removes the "Bearer " prefix if present.
     */
    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        // Get the token from the "Authorization" header and remove the "Bearer " prefix if present
        var token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7)); // "Bearer " has 7 characters (including the space)
        }
        return Optional.empty();
    }
}
