/**
 * ImageController Class
 *
 * This class is responsible for handling image-related operations, such as uploading, serving, and retrieving image data.
 */
package com.example.webshopbackend.controller;

import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.repository.ImageRepository;
import com.example.webshopbackend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final StorageService storageService;
    private final ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Value("${file.upload-dir}")
    public String uploadDir;

    @Autowired
    public ImageController(StorageService storageService, ImageRepository imageRepository) {
        this.storageService = storageService;
        this.imageRepository = imageRepository;
    }

    /**
     * Upload an Image
     *
     * This method handles the uploading of image files to the server. It stores the image in the specified
     * storage directory and saves the image's path to the database.
     *
     * @param file The MultipartFile representing the image file to upload.
     * @return The uploaded Image object.
     * @throws IOException if there is an issue with file handling.
     */
    @PostMapping
    @ResponseBody
    public Image uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        // Obtain the storage directory and filename
        Path storageDirectory = Paths.get(uploadDir);
        String filename = file.getOriginalFilename();

        // Log the paths for debugging
        logger.debug("Storage Directory: {}", storageDirectory);
        logger.debug("Filename: {}", filename);

        // Store the file in the storage directory
        Path filePath = storageDirectory.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Create and save the Image object to the database
        Image image = new Image();
        image.setPath(filename);
        imageRepository.save(image);

        return image;
    }

    /**
     * Serve an Image by ID
     *
     * This method retrieves and serves an image based on its ID. If the image is found, it is returned as a ResponseEntity.
     *
     * @param id The ID of the image to retrieve.
     * @return ResponseEntity with the image if found, or a "not found" response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Image> serveImage(@PathVariable int id) {
        Optional<com.example.webshopbackend.model.Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();

            return ResponseEntity.ok().body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get Paths of All Images
     *
     * This method retrieves the paths of all images stored in the database or storage and returns them as a list.
     *
     * @return List of image paths.
     */
    @GetMapping("/all")
    public List<String> getAllImagePaths() {
        List<String> imagePaths = new ArrayList<>();

        // Retrieve all images from the database or storage
        List<Image> images = imageRepository.findAll();

        for (Image image : images) {
            imagePaths.add(image.getPath());
        }
        return imagePaths;
    }
}
