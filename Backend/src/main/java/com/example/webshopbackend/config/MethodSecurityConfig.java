/**
 * MethodSecurityConfig Class
 *
 * This class is a configuration class for enabling global method-level security in the web application. It allows you
 * to use annotations like `@PreAuthorize` and `@PostAuthorize` to secure methods based on user roles and permissions.
 */
package com.example.webshopbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
}
