package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.OriginalDTO;

import java.util.List;
import java.util.Optional;

public interface OriginalService {

    /**
     * Save an Original
     *
     * @param originalDTO The OriginalDTO representing the original product to be saved.
     * @return The OriginalDTO representing the saved original product.
     */
    OriginalDTO save(OriginalDTO originalDTO);

    /**
     * Find all Originals
     *
     * @return A list of OriginalDTO objects representing all available original products.
     */
    List<OriginalDTO> findAll();

    /**
     * Find an Original by ID
     *
     * @param id The unique identifier (typically an ID) of the original product to be found.
     * @return An Optional containing the OriginalDTO if found, or an empty Optional if no matching original product is found.
     */
    Optional<OriginalDTO> findById(Long id);

    /**
     * Delete an Original
     *
     * @param originalDTO The OriginalDTO representing the original product to be deleted.
     */
    void delete(OriginalDTO originalDTO);
}
