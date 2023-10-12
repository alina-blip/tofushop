package com.example.webshopbackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.webshopbackend.dto.UserDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAllUsersEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSaveUserEndpoint() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John");
        userDTO.setSurname("Doe");
        userDTO.setPassword("12345");
        userDTO.setCountry("Graz");
        userDTO.setEmail("John@Doe.com");
        userDTO.setHousenumber("1A");
        userDTO.setStreet("Getreidegasse");
        userDTO.setPostalcode("1030");

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testDeleteUserEndpoint() throws Exception {
        long userIdToDelete = 9;

        String adminToken = generateAdminToken();

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + userIdToDelete)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String generateAdminToken() {
        String secretKey = "thisismysecretkey";
        int subject = 9;
        String email = "admin@example.com";
        Instant expiration = Instant.now().plus(Duration.ofDays(1)); // Set a 1-day expiration
        List<String> roles = Collections.singletonList("ADMIN");


        String token = JWT.create()
                .withSubject(String.valueOf(subject))
                .withClaim("e", email)
                .withClaim("a", roles)
                .withExpiresAt(Date.from(expiration)) // Include the expiration claim
                .sign(Algorithm.HMAC256(secretKey));

        System.out.println("Generated Token: " + token);

        return token;
    }


}


