package com.example.webshopbackend.controller;


import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.repository.ImageRepository;
import com.example.webshopbackend.service.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ImageRepository imageRepository;

    @MockBean
    private StorageService storageService;

    @Test
    public void testImageUploadAndRetrieval() throws Exception {
        // Create a sample image file to upload
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", "sample.jpg", MediaType.IMAGE_JPEG_VALUE, "Test image".getBytes());

        // Upload the image
        mockMvc.perform(MockMvcRequestBuilders.multipart("/images")
                        .file(imageFile))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Retrieve the uploaded image
        List<Image> images = imageRepository.findAll();
        Image uploadedImage = images.get(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/images/" + uploadedImage.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Get a list of all image paths
        mockMvc.perform(MockMvcRequestBuilders.get("/images/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

