/**
 * OriginalController Class
 *
 * This class serves as the controller for managing original products in the web application. It provides endpoints for
 * saving, updating, deleting, and retrieving original product information.
 *
 * Originals are the products that are offered in the Webshop.
 */
package com.example.webshopbackend.controller;

import java.util.List;
import java.util.Optional;

import com.example.webshopbackend.dto.OriginalDTO;
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

    /**
     * Save an Original Product
     *
     * This endpoint allows the user to save a new original product by providing the necessary product information.
     *
     * @param originalDTO The OriginalDTO containing product information to be saved.
     * @return The saved OriginalDTO.
     */
    @PostMapping("")
    public OriginalDTO save(@RequestBody OriginalDTO originalDTO) {
        return service.save(originalDTO);
    }

    /**
     * Get All Original Products
     *
     * This endpoint retrieves a list of all original products in the database.
     *
     * @return List of OriginalDTOs representing all original products.
     */
    @GetMapping("")
    public List<OriginalDTO> all() {
        List<OriginalDTO> originalDTOs = service.findAll();
        return originalDTOs;
    }

    /**
     * Get an Original Product by ID
     *
     * This endpoint retrieves a specific original product by its unique ID.
     *
     * @param id The unique identifier of the original product to retrieve.
     * @return An Optional containing the OriginalDTO if found, or an empty Optional if not found.
     */
    @GetMapping("/{id}")
    public Optional<OriginalDTO> one(@PathVariable long id) {
        return service.findById(id);
    }

    /**
     * Update an Original Product
     *
     * This endpoint allows the user to update an existing original product by providing the updated product information.
     *
     * @param id The unique identifier of the original product to update.
     * @param updatedOriginalDTO The OriginalDTO containing updated product information.
     * @return The updated OriginalDTO.
     */
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

    /**
     * Delete an Original Product
     *
     * This endpoint allows the user to delete an existing original product by its unique ID.
     *
     * @param id The unique identifier of the original product to delete.
     * @return ResponseEntity indicating the result of the delete operation.
     */
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
