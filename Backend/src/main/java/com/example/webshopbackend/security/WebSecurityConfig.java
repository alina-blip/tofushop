package com.example.webshopbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    /**
     * Configure security filters and authorization rules for your application.
     *
     * @param http The HttpSecurity object to configure.
     * @return A SecurityFilterChain for your application's security.
     * @throws Exception If there's an issue with configuration.
     */
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        // Add the custom JWT authentication filter before the UsernamePasswordAuthenticationFilter

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin().disable()
                .securityMatcher("/**")
                .authorizeHttpRequests(registry -> {
                    try {
                        registry
                                .requestMatchers("/Frontend/Dashboard.html").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").permitAll()
                                .requestMatchers("/cart/**").permitAll()
                                .requestMatchers("/user/login").permitAll()
                                .requestMatchers("/user/auth/login/**").permitAll()
                                .requestMatchers("/original/**").permitAll()
                                .requestMatchers("/secured").permitAll()
                                .requestMatchers("/uploads/panda-4.jpg").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/images/all").permitAll()
                                .requestMatchers("/original/**").permitAll()
                                .requestMatchers("/cart/**").permitAll()
                                .requestMatchers("/uploads/**").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/dashboard/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                                .and()
                                .exceptionHandling()
                                .accessDeniedPage("/access-denied");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );

        return http.build();
    }
}
