package com.example.webshopbackend.controller;


import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.repository.ImageRepository;
import com.example.webshopbackend.service.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ImageUnitControllerTest {

    @InjectMocks
    private ImageController imageController;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private StorageService storageService;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this);
        Image mockImage = new Image();
        mockImage.setId(1);
        // Define behavior for your mock repository
        when(imageRepository.findById(1)).thenReturn(Optional.of(mockImage));
    }



    @Test
    public void testServeImage() {
        // Prepare a mock Image
        Image mockImage = new Image();
        mockImage.setId(1);

        // Mock the behavior of imageRepository
        when(imageRepository.findById(1)).thenReturn(Optional.of(mockImage));

        // Test serving an image by ID
        ResponseEntity<Image> response = imageController.serveImage(1);

        // Assert that the response status is OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Assert that the returned image ID matches the expected value
        assertEquals(1, response.getBody().getId());
    }

    @Test
    public void testGetAllImagePaths() {
        // Prepare a list of mock image paths
        List<String> mockPaths = Arrays.asList("image1.jpg", "image2.jpg");

        // Create mock Image objects with expected paths
        Image image1 = new Image();
        image1.setPath("image1.jpg");
        Image image2 = new Image();
        image2.setPath("image2.jpg");

        // Mock the behavior of imageRepository to return the mock Image objects
        when(imageRepository.findAll()).thenReturn(Arrays.asList(image1, image2));

        // Test getting all image paths
        List<String> imagePaths = imageController.getAllImagePaths();

        // Assert that the returned image paths match the mock paths
        assertEquals(mockPaths, imagePaths);
    }
}

