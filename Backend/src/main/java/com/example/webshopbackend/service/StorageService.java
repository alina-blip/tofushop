/**
 * StorageService Interface
 *
 * This interface defines the contract for managing and storing images in the web application. It provides methods for
 * retrieving, storing, and deleting image files, as well as retrieving information about images.
 */
package com.example.webshopbackend.service;

import com.example.webshopbackend.exception.StorageException;
import com.example.webshopbackend.exception.StorageFileNotFoundException;
import com.example.webshopbackend.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StorageService {

    /**
     * Retrieve a list of all Image objects.
     *
     * @return List of Image objects representing all images in the storage.
     */
    List<Image> findAll();

    /**
     * Retrieve an Image by its unique ID.
     *
     * @param id The unique identifier of the Image.
     * @return An Optional containing the Image if found, or an empty Optional if not found.
     */
    Optional<Image> findById(Long id);

    /**
     * Store an uploaded image file.
     *
     * @param file The MultipartFile representing the image file to be stored.
     * @throws StorageException if there is an issue with storing the file.
     */
    void store(MultipartFile file) throws StorageException;

    /**
     * Load an image file as a Resource by its filename.
     *
     * @param filename The name of the image file to be loaded as a Resource.
     * @return A Resource representing the image file.
     * @throws StorageFileNotFoundException if the file is not found.
     */
    Resource loadAsResource(String filename) throws StorageFileNotFoundException;

    /**
     * Delete an image file by its filename.
     *
     * @param filename The name of the image file to be deleted.
     * @throws StorageFileNotFoundException if the file is not found.
     */
    void delete(String filename) throws StorageFileNotFoundException;
}
