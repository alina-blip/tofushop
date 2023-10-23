package com.example.webshopbackend.security;

import com.example.webshopbackend.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Represents an authenticated user.
 * This class is used for storing user-related data, including their ID, email, and roles.
 */
@Getter
@Builder
public class UserPrincipal implements UserDetails {

    private final Long userId;

    private final String email;

    private final UserRole role;

    public Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // Password is not stored, as it's provided by the JWT token.
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // User account is always considered non-expired.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // User account is never locked.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // User's credentials are never considered expired.
    }

    @Override
    public boolean isEnabled() {
        return true; // User is always considered enabled.
    }

}
