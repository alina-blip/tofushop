/**
 * HelloController Class
 *
 * This class serves as a controller for demonstrating secured and admin routes in the web application. It provides
 * endpoints that can only be accessed by authenticated users with specific roles.
 */
package com.example.webshopbackend.controller;

import org.springframework.security.core.GrantedAuthority;
import com.example.webshopbackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HelloController {

    /**
     * Secured Endpoint
     *
     * This endpoint is accessible to authenticated users and provides information about the user, such as their roles,
     * email, and user ID.
     *
     * @param principal The UserPrincipal representing the authenticated user.
     * @return A message containing user information.
     */
    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        List<String> authorities = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return "If you see this, then you are logged in as " + authorities.get(0) + " with the Mail: " +
                principal.getEmail() + " User Id: " + principal.getUserId();
    }

    /**
     * Admin Endpoint
     *
     * This endpoint is accessible to authenticated users with admin roles and provides information about the admin user,
     * including their roles, email, and user ID.
     *
     * @param principal The UserPrincipal representing the authenticated admin user.
     * @return A message containing admin user information.
     */
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        List<String> authorities = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return "If you see this, then you are logged in as " + authorities.get(0) + " with the Mail: " +
                principal.getEmail() + " User Id: " + principal.getUserId();
    }
}
