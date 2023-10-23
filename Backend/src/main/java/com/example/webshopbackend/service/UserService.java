package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing User entities.
 */
public interface UserService {

    /**
     * Saves a new User entity.
     *
     * @param userDTO The DTO representing the User to be saved.
     * @return The saved User as a DTO.
     */
    UserDTO save(UserDTO userDTO);

    /**
     * Retrieves a User entity by email.
     *
     * @param email The email of the User to retrieve.
     * @return The UserDTO representing the User if found, or null if not.
     */
    UserDTO findByEmail(String email);

    /**
     * Retrieves a list of all User entities.
     *
     * @return A list of UserDTOs representing the User entities.
     */
    List<UserDTO> findAll();

    /**
     * Retrieves a User entity by its ID.
     *
     * @param id The ID of the User to retrieve.
     * @return An Optional containing the UserDTO if found, or empty if not.
     */
    Optional<UserDTO> findById(Long id);

    /**
     * Deletes a User entity.
     *
     * @param userDTO The DTO representing the User to be deleted.
     */
    void delete(UserDTO userDTO);
}
