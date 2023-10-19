package com.example.webshopbackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.webshopbackend.dto.UserDTO;

import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // Add this annotation
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

        @BeforeAll
        void setUp() throws Exception {
            // Create and add the first user
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setId(1L); // Use the current value of the counter
            userDTO1.setName("John");
            userDTO1.setSurname("Doe");
            userDTO1.setPassword("12345");
            userDTO1.setCountry("Graz");
            userDTO1.setEmail("John@Doe.com"); // Append counter to make email unique
            userDTO1.setHousenumber("1A");
            userDTO1.setStreet("Getreidegasse");
            userDTO1.setPostalcode("1030");

            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userDTO1)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            // Create and add the second user
            UserDTO userDTO2 = new UserDTO();
            userDTO2.setId(2L); // Use the current value of the counter
            userDTO2.setName("Alina");
            userDTO2.setSurname("Sorger");
            userDTO2.setPassword("Passwort");
            userDTO2.setCountry("Wien");
            userDTO2.setEmail("alina@sorger.com"); // Append counter to make email unique
            userDTO2.setHousenumber("1");
            userDTO2.setStreet("Wiengasse");
            userDTO2.setPostalcode("1020");

            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userDTO2)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            // Create and add the third user
            UserDTO userDTO3 = new UserDTO();
            userDTO3.setId(3L); // Use the current value of the counter
            userDTO3.setName("Alice");
            userDTO3.setSurname("Smith");
            userDTO3.setPassword("Password123");
            userDTO3.setCountry("Vienna");
            userDTO3.setEmail("alice@smith.com"); // Append counter to make email unique
            userDTO3.setHousenumber("2B");
            userDTO3.setStreet("Main Street");
            userDTO3.setPostalcode("1010");

            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userDTO3)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            // Create and add the fourth user
            UserDTO userDTO4 = new UserDTO();
            userDTO4.setId(4L); // Use the current value of the counter
            userDTO4.setName("Bob");
            userDTO4.setSurname("Johnson");
            userDTO4.setPassword("Test123");
            userDTO4.setCountry("New York");
            userDTO4.setEmail("bob@johnson.com"); // Append counter to make email unique
            userDTO4.setHousenumber("3C");
            userDTO4.setStreet("Broadway");
            userDTO4.setPostalcode("10001");

            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userDTO4)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            // Create and add the fifth user
            UserDTO userDTO5 = new UserDTO();
            userDTO5.setId(5L); // Use the current value of the counter
            userDTO5.setName("Eva");
            userDTO5.setSurname("Brown");
            userDTO5.setPassword("Pass456");
            userDTO5.setCountry("Los Angeles");
            userDTO5.setEmail("eva@brown.com"); // Append counter to make email unique
            userDTO5.setHousenumber("4D");
            userDTO5.setStreet("Hollywood Blvd");
            userDTO5.setPostalcode("90001");

            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userDTO5)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

    @Test
    void testGetUsersEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print()); // This line prints the response

    }
    @Test
    void testSaveUserEndpoint() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(5L);
        userDTO.setName("Julie");
        userDTO.setSurname("Maier");
        userDTO.setPassword("12PW12");
        userDTO.setCountry("Graz");
        userDTO.setEmail("julie@maier.at");
        userDTO.setHousenumber("1");
        userDTO.setStreet("Gegasse");
        userDTO.setPostalcode("1010");

        System.out.println("THIS IS THE ID I POST IN THE DATABASE" + userDTO.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @Test
    void testDeleteUserEndpoint() throws Exception {


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

    @Test
    void testUpdateUserEndpoint() throws Exception {

        // Update the user's information
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setId(2L); // Set the ID of the user to update
        updatedUserDTO.setName("Suri");
        updatedUserDTO.setSurname("Ko");
        updatedUserDTO.setPassword("2398767");
        updatedUserDTO.setCountry("Berlin");
        updatedUserDTO.setEmail("suri@ko.com");
        updatedUserDTO.setHousenumber("2B");
        updatedUserDTO.setStreet("Wenston Street");
        updatedUserDTO.setPostalcode("1040A");
        updatedUserDTO.setRole(UserRole.USER);

        String adminToken = generateAdminToken();


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/user/2")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .content(new ObjectMapper().writeValueAsString(updatedUserDTO));

        mockMvc.perform(request)
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

    private String generateUserToken() {
        String secretKey = "thisismysecretkey";
        int subject = 1;
        String email = "user@example.com";
        Instant expiration = Instant.now().plus(Duration.ofDays(1));
        List<String> roles = Collections.singletonList("USER");


        String token = JWT.create()
                .withSubject(String.valueOf(subject))
                .withClaim("e", email)
                .withClaim("a", roles)
                .withExpiresAt(Date.from(expiration))
                .sign(Algorithm.HMAC256(secretKey));

        System.out.println("Generated Token: " + token);

        return token;
    }

    @Test
    void testUserLoginEndpoint() throws Exception {


        UserDTO loginUserDTO = new UserDTO();
        loginUserDTO.setEmail("alice@smith.com");
        loginUserDTO.setPassword("Password123");

        String userToken = generateUserToken();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginUserDTO))
                .header("Authorization", "Bearer " + userToken);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void testAdminLoginEndpoint() throws Exception {
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setId(4L); // Set the ID of the user to update
        updatedUserDTO.setName("Bob");
        updatedUserDTO.setSurname("Johnson");
        updatedUserDTO.setPassword("newPassword");
        updatedUserDTO.setCountry("Vienna");
        updatedUserDTO.setEmail("bob@johnson.com");
        updatedUserDTO.setHousenumber("2B");
        updatedUserDTO.setStreet("Left Street");
        updatedUserDTO.setPostalcode("1040");
        updatedUserDTO.setRole(UserRole.ADMIN);

        String adminToken = generateAdminToken();

        MockHttpServletRequestBuilder PutRequest = MockMvcRequestBuilders.put("/user/4")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .content(new ObjectMapper().writeValueAsString(updatedUserDTO));

        mockMvc.perform(PutRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());



        UserDTO loginUserDTO = new UserDTO();
        loginUserDTO.setEmail("bob@johnson.com");
        loginUserDTO.setPassword("newPassword");

        String userToken = generateUserToken();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginUserDTO))
                .header("Authorization", "Bearer " + userToken);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testChangePassword() throws Exception {
        // Updating only the password
        UserDTO userDTO = new UserDTO();
        userDTO.setId(4L);
        userDTO.setName("Bob");
        userDTO.setSurname("Johnson");
        userDTO.setPassword("UpdatedPassword123");
        userDTO.setCountry("New York");
        userDTO.setEmail("bob@johnson.com");
        userDTO.setHousenumber("3C");
        userDTO.setStreet("Broadway");
        userDTO.setPostalcode("10001");
       userDTO.setRole(UserRole.USER);

        String adminToken = generateAdminToken();


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/user/4")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .content(new ObjectMapper().writeValueAsString(userDTO));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
}

    @Test
    void testChangeEmail() throws Exception {
        // Updating only the email
        UserDTO userDTO5 = new UserDTO();
        userDTO5.setId(5L);
        userDTO5.setName("Eva");
        userDTO5.setSurname("Brown");
        userDTO5.setPassword("Pass456");
        userDTO5.setCountry("Los Angeles");
        userDTO5.setEmail("evaneemail@brown.com");
        userDTO5.setHousenumber("4D");
        userDTO5.setStreet("Hollywood Blvd");
        userDTO5.setPostalcode("90001");
        userDTO5.setRole(UserRole.USER);

        String adminToken = generateAdminToken();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/user/5")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .content(new ObjectMapper().writeValueAsString(userDTO5));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}


