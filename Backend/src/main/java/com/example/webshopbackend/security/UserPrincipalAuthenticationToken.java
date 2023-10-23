package com.example.webshopbackend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
/**
 * Custom authentication token representing an authenticated user.
 * This class extends AbstractAuthenticationToken and holds a UserPrincipal object.
 */
public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final UserPrincipal principal;

    /**
     * Constructs a new UserPrincipalAuthenticationToken with the provided UserPrincipal.
     *
     * @param principal The UserPrincipal representing the authenticated user.
     */
    public UserPrincipalAuthenticationToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials are stored, as the user is authenticated via JWT token.
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}
