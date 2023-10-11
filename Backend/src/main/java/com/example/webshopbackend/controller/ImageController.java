package com.example.webshopbackend.controller;

import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.repository.ImageRepository;
import com.example.webshopbackend.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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

    @Value("${file.upload-dir}") // Inject the property value from application properties
    private String uploadDir;
    private final StorageService storageService;
    private final ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    public ImageController(StorageService storageService, ImageRepository imageRepository) {
        this.storageService = storageService;
        this.imageRepository = imageRepository;
    }

    @PostMapping
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Path storageDirectory = Paths.get(uploadDir);
        String filename = file.getOriginalFilename();

        // Log the paths for debugging
        logger.debug("Storage Directory: {}", storageDirectory);
        logger.debug("Filename: {}", filename);

        // Store the file in the storage directory
        Path filePath = storageDirectory.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Image image = new Image();
        image.setPath(filePath.toString());
        imageRepository.save(image);

        return "successfully uploaded";
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable int id) {
        Optional<com.example.webshopbackend.model.Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            Resource file = (Resource) storageService.loadAsResource(image.getPath());

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
