package com.example.webshopbackend.controller;
import java.awt.*;
import java.util.List;
import java.util.Optional;

import com.example.webshopbackend.dto.OriginalDTO;
import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.repository.ImageRepository;
import com.example.webshopbackend.service.OriginalService;
import com.example.webshopbackend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/original")
public class OriginalController {

    private final OriginalService service;
    private final StorageService storageService;

    @Autowired
    OriginalController(OriginalService service, StorageService storageService) {
        this.service = service;
        this.storageService = storageService;
    }
    @PostMapping("")
    public OriginalDTO save(@RequestBody OriginalDTO originalDTO) {
        return service.save(originalDTO);
    }

    @GetMapping("")
    public List<OriginalDTO> all() {
        List<OriginalDTO> originalDTOs = service.findAll();
        return originalDTOs;
    }

    @GetMapping("/{id}")
    public Optional<OriginalDTO> one (@PathVariable long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public OriginalDTO update(@PathVariable long id, @RequestBody OriginalDTO updatedOriginalDTO) {
        Optional<OriginalDTO> originalDTOOptional = service.findById(id);
        if (originalDTOOptional.isPresent()) {
            OriginalDTO originalDTO = originalDTOOptional.get();
            originalDTO.setDescription(updatedOriginalDTO.getDescription());
            originalDTO.setCategory(updatedOriginalDTO.getCategory());
            originalDTO.setMaterial(updatedOriginalDTO.getMaterial());
            originalDTO.setPrice((float) updatedOriginalDTO.getPrice());
            originalDTO.setSize(updatedOriginalDTO.getSize());
            originalDTO.setQuantity(updatedOriginalDTO.getQuantity());
            originalDTO.setTitle(updatedOriginalDTO.getTitle());
            originalDTO.setUrl(updatedOriginalDTO.getUrl());
            originalDTO.setImageId(updatedOriginalDTO.getImageId());
            return service.save(originalDTO);
        } else {
            throw new RuntimeException("Original not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Optional<OriginalDTO> originalDTOOptional = service.findById(id);
        if (originalDTOOptional.isPresent()) {
            service.delete(originalDTOOptional.get());
            return ResponseEntity.ok("Deleted successfully"); // Return a 200 OK response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Original not found with id: " + id);
        }
    }

}
