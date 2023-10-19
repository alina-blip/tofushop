package com.example.webshopbackend.controller;

import com.example.webshopbackend.dto.OriginalDTO;
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

@SpringBootTest
@AutoConfigureMockMvc
public class OriginalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void testGetOriginalsEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/original")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testSaveOriginalEndpoint() throws Exception {
        OriginalDTO originalDTO = new OriginalDTO();
        originalDTO.setId(1);
        originalDTO.setTitle("Test Original");
        originalDTO.setDescription("This is a test original");
        originalDTO.setCategory("PRINT");
        originalDTO.setMaterial("Test Material");
        originalDTO.setPrice(19.99F);
        originalDTO.setSize("Test Size");
        originalDTO.setQuantity(10);
        originalDTO.setUrl("url");
        originalDTO.setImageId(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/original")
                        .content(asJsonString(originalDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateOriginalEndpoint() throws Exception {
        // First, create an item to update
        OriginalDTO originalDTO = new OriginalDTO();
        originalDTO.setId(2);
        originalDTO.setTitle("Test Original");
        originalDTO.setDescription("This is a test original");
        originalDTO.setCategory("PRINT");
        originalDTO.setMaterial("Test Material");
        originalDTO.setPrice(19.99F);
        originalDTO.setSize("Test Size");
        originalDTO.setQuantity(10);
        originalDTO.setUrl("url");
        originalDTO.setImageId(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/original")
                        .content(asJsonString(originalDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Then, update the item
        OriginalDTO updatedOriginalDTO = new OriginalDTO();
        updatedOriginalDTO.setId(2);
        updatedOriginalDTO.setTitle("Updated Test Original");
        updatedOriginalDTO.setDescription("This is an updated test original");
        updatedOriginalDTO.setCategory("STICKER");
        updatedOriginalDTO.setMaterial("Updated Test Material");
        updatedOriginalDTO.setPrice(29.99F);
        updatedOriginalDTO.setSize("Updated Test Size");
        updatedOriginalDTO.setQuantity(15);
        updatedOriginalDTO.setUrl("updated_url");
        updatedOriginalDTO.setImageId(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/original/2")  // Assuming you want to update an item with ID 1
                        .content(asJsonString(updatedOriginalDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testDeleteOriginalEndpoint() throws Exception {
        // First, create an item to delete
        OriginalDTO originalDTO = new OriginalDTO();
        originalDTO.setId(1);
        originalDTO.setTitle("Item to Delete");
        originalDTO.setDescription("This item should be deleted");
        originalDTO.setCategory("STICKER");
        originalDTO.setMaterial("Delete Material");
        originalDTO.setPrice(9.99F);
        originalDTO.setSize("Delete Size");
        originalDTO.setQuantity(5);
        originalDTO.setUrl("delete_url");
        originalDTO.setImageId(2);

        mockMvc.perform(MockMvcRequestBuilders.post("/original")
                        .content(asJsonString(originalDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Then, delete the created item
        mockMvc.perform(MockMvcRequestBuilders.delete("/original/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetOriginalByIdEndpoint() throws Exception {
        // Create an item to retrieve by ID
        OriginalDTO originalDTO = new OriginalDTO();
        originalDTO.setId(1);
        originalDTO.setTitle("Item to Retrieve");
        originalDTO.setDescription("This item should be retrieved by ID");
        originalDTO.setCategory("PRINT");
        originalDTO.setMaterial("Retrieve Material");
        originalDTO.setPrice(14.99F);
        originalDTO.setSize("Retrieve Size");
        originalDTO.setQuantity(8);
        originalDTO.setUrl("retrieve_url");
        originalDTO.setImageId(3);

        mockMvc.perform(MockMvcRequestBuilders.post("/original")
                        .content(asJsonString(originalDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Retrieve the created item by ID
        mockMvc.perform(MockMvcRequestBuilders.get("/original/1")) // Assuming you want to retrieve an item with ID 2
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
