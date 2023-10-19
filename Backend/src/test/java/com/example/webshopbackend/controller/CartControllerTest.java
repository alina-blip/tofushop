package com.example.webshopbackend.controller;

import com.example.webshopbackend.dto.OriginalDTO;
import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.Category;
import com.example.webshopbackend.model.Original;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.webshopbackend.dto.CartDTO;
import com.example.webshopbackend.service.CartService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.webshopbackend.controller.OriginalControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // Add this
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private CartService cartService;
    @BeforeAll
    public void setUp() throws Exception {
        Original original1 = new Original();
        original1.setId(1L);
        original1.setTitle("Original 1");
        original1.setSize("Large");
        original1.setMaterial("Canvas");
        original1.setDescription("Description 1");
        original1.setPrice(50);
        original1.setUrl("image1.jpg");
        original1.setQuantity(10);
        original1.setImageId(1);
        original1.setCategory(Category.ORIGINAL);

        mockMvc.perform(MockMvcRequestBuilders.post("/original")
                        .content(asJsonString(original1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Original original2 = new Original();
        original2.setId(2L);
        original2.setTitle("Original 2");
        original2.setSize("Medium");
        original2.setMaterial("Paper");
        original2.setDescription("Description 2");
        original2.setPrice(30);
        original2.setUrl("image2.jpg");
        original2.setQuantity(20);
        original2.setImageId(2);
        original2.setCategory(Category.PRINT);

        mockMvc.perform(MockMvcRequestBuilders.post("/original")
                        .content(asJsonString(original2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

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

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(1L);
        cartDTO.setUserId(1L);
        cartDTO.setDate(LocalDate.parse("2020-02-02"));

        List<Original> originalList = new ArrayList<>();

        cartDTO.setOriginals(originalList);

        // Serialize the CartDTO to JSON using the configured ObjectMapper
        String cartJson = objectMapper.writeValueAsString(cartDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void createEmptyCart() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(2L);
        cartDTO.setUserId(1L);
        cartDTO.setDate(LocalDate.parse("2020-02-02"));

        List<Original> originalList = new ArrayList<>();

        cartDTO.setOriginals(originalList);

        // Serialize the CartDTO to JSON using the configured ObjectMapper
        String cartJson = objectMapper.writeValueAsString(cartDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void putProductInCart() throws Exception {
        CartDTO updateCartDTO = new CartDTO();
        updateCartDTO.setCart_id(1L);
        updateCartDTO.setUserId(1L);
        updateCartDTO.setDate(LocalDate.parse("2020-02-02"));

        List<Original> originalList = new ArrayList<>();

        // Add existing original items to the list
        Original original1 = new Original();
        original1.setId(1L); // The ID of the existing original
        originalList.add(original1);

        Original original2 = new Original();
        original2.setId(2L); // The ID of the existing original
        originalList.add(original2);
        updateCartDTO.setOriginals(originalList);


        // Serialize the CartDTO to JSON using the configured ObjectMapper
        String cartJson = objectMapper.writeValueAsString(updateCartDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/cart/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void deleteOneProductInCart() throws Exception {
        CartDTO updateCartDTO = new CartDTO();
        updateCartDTO.setCart_id(1L);
        updateCartDTO.setUserId(1L);
        updateCartDTO.setDate(LocalDate.parse("2020-02-02"));

        List<Original> originalList = new ArrayList<>();

        // Add existing original items to the list
        Original original1 = new Original();
        original1.setId(1L); // The ID of the existing original
        originalList.add(original1);

        // dont add original 2

        updateCartDTO.setOriginals(originalList);


        // Serialize the CartDTO to JSON using the configured ObjectMapper
        String cartJson = objectMapper.writeValueAsString(updateCartDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/cart/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    //IntegrationTest
    @Test
    void testGetCartEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


}
