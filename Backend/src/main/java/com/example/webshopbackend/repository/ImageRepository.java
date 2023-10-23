/**
 * ImageRepository Interface
 *
 * This interface defines data access operations for the Image entity in the web application. It extends the JpaRepository
 * interface, providing basic CRUD (Create, Read, Update, Delete) operations for Image objects.
 */
package com.example.webshopbackend.repository;

import com.example.webshopbackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    /**
     * Retrieve a list of all Image objects.
     *
     * @return List of Image objects representing all images stored in the repository.
     */
    @Override
    List<Image> findAll();

    /**
     * Retrieve an Image by its unique ID.
     *
     * @param id The unique identifier of the Image.
     * @return An Optional containing the Image if found, or an empty Optional if not found.
     */
    Optional<Image> findById(long id);
}
