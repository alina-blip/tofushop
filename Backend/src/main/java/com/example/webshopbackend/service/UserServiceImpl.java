package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing User entities.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves a user to the database after hashing the password.
     *
     * @param userDTO The user DTO to save.
     * @return The saved UserDTO.
     * @throws RuntimeException if there is an issue with saving the user.
     */
    @Override
    public UserDTO save(UserDTO userDTO) {
        try {
            // Hash the password before saving
            String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
            userDTO.setPassword(hashedPassword);

            User user = convertToEntity(userDTO);
            User savedUser = repository.save(user);

            return convertToDTO(savedUser);
        } catch (DataAccessException ex) {
            // Handle the exception, log, and possibly rethrow a custom exception.
            throw new RuntimeException("Failed to save User", ex);
        }
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of UserDTOs representing all users.
     */
    @Override
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a user by their email address.
     *
     * @param email The email address to search for.
     * @return The UserDTO if found, or null if not found.
     */
    @Override
    public UserDTO findByEmail(String email) {
        User user = repository.findByEmail(email);
        return (user != null) ? convertToDTO(user) : null;
    }

    /**
     * Finds a user by their ID.
     *
     * @param id The user ID to search for.
     * @return An Optional containing the UserDTO if found, or an empty Optional if not found.
     */
    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.map(this::convertToDTO);
    }

    /**
     * Deletes a user from the database.
     *
     * @param userDTO The UserDTO to be deleted.
     */
    @Override
    public void delete(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        repository.delete(user);
    }

    // Helper methods to convert between UserDTO and User
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setStreet(user.getStreet());
        userDTO.setHousenumber(user.getHousenumber());
        userDTO.setPostalcode(user.getPostalcode());
        userDTO.setCountry(user.getCountry());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole()); // Assuming role is an enum
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setStreet(userDTO.getStreet());
        user.setHousenumber(userDTO.getHousenumber());
        user.setPostalcode(userDTO.getPostalcode());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(UserRole.valueOf(userDTO.getRole().toString())); // Assuming role is an enum
        return user;
    }
}
