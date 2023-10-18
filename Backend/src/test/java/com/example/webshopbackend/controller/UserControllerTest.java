package com.example.webshopbackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.webshopbackend.dto.UserDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
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
    @Order(2)
    void testGetUsersEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print()); // This line prints the response

    }
    @Test
    @Order(1)
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

        System.out.println("THIS IS THE ID I POST IN THE DATABASE" + userDTO.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @Order(3)
    void testDeleteUserEndpoint() throws Exception {
        // Create a user in the database
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

        // Save user to database
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // delete the user
        long userIdToDelete = 1;

        String requestJson = "{\"id\": " + userIdToDelete + "}";

        System.out.println(requestJson);
        String adminToken = generateAdminToken();

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + userIdToDelete)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    private String generateAdminToken() {
        String secretKey = "thisismysecretkey";
        int subject = 1;
        String email = "admin@example.com";
        Instant expiration = Instant.now().plus(Duration.ofDays(1));
        List<String> roles = Collections.singletonList("ADMIN");


        String token = JWT.create()
                .withSubject(String.valueOf(subject))
                .withClaim("e", email)
                .withClaim("a", roles)
                .withExpiresAt(Date.from(expiration))
                .sign(Algorithm.HMAC256(secretKey));

        System.out.println("Generated Token: " + token);

        return token;
    }


}


