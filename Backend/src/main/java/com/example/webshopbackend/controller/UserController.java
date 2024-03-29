/**
 * UserController Class
 *
 * This class serves as the controller for managing user-related operations in the web application. It provides endpoints
 * for retrieving, saving, updating, and deleting user information, as well as user authentication and authorization.
 */
package com.example.webshopbackend.controller;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.security.JwtIssuer;
import com.example.webshopbackend.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final JwtIssuer jwtIssuer;
    private final UserService service;

    @Autowired
    UserController(JwtIssuer jwtIssuer, UserService service) {
        this.jwtIssuer = jwtIssuer;
        this.service = service;
    }

    /**
     * Get All Users
     *
     * This endpoint retrieves a list of all user profiles in the system.
     *
     * @return List of UserDTOs representing all users.
     */
    @GetMapping("")
    public List<UserDTO> all() {
        List<UserDTO> userDTOs = service.findAll();
        return userDTOs;
    }

    /**
     * Save a User
     *
     * This endpoint allows the user to save a new user profile by providing the necessary user information.
     * The user is assigned the "USER" role by default.
     *
     * @param userDTO The UserDTO containing user information to be saved.
     * @return The saved UserDTO.
     */
    @PostMapping("")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        userDTO.setRole(UserRole.USER);
        return service.save(userDTO);
    }

    /**
     * User Login
     *
     * This endpoint handles user authentication and login. Users provide their email and password, and if the
     * credentials are valid, they receive an access token for future authorization.
     *
     * @param userDTO The UserDTO containing login credentials.
     * @return ResponseEntity containing an access token if authentication is successful, or a 401 Unauthorized response.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
        UserDTO existingUser = service.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            // Compare the provided login password with the stored hashed password
            if (BCrypt.checkpw(userDTO.getPassword(), existingUser.getPassword())) {

                // Include user roles as a claim in the JWT token
                List<String> userRoles = Collections.singletonList(existingUser.getRole().toString());

                var token = jwtIssuer.issue(1, userDTO.getEmail(), userRoles); // Pass userRoles to include in the token

                return ResponseEntity.ok(LoginResponse.builder()
                        .accessToken(token)
                        .build());

            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse.builder()
                .build());
    }

    /**
     * Update a User
     *
     * This endpoint allows the user to update their own profile information or an admin to update any user's profile.
     *
     * @param id The unique identifier of the user to update.
     * @param updatedUserDTO The UserDTO containing updated user information.
     * @return The updated UserDTO.
     */
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable long id, @RequestBody UserDTO updatedUserDTO) {
        Optional<UserDTO> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            UserDTO userDTO = userOptional.get();
            userDTO.setName(updatedUserDTO.getName());
            userDTO.setSurname(updatedUserDTO.getSurname());
            userDTO.setStreet(updatedUserDTO.getStreet());
            userDTO.setHousenumber(updatedUserDTO.getHousenumber());
            userDTO.setPostalcode(updatedUserDTO.getPostalcode());
            userDTO.setCountry(updatedUserDTO.getCountry());
            userDTO.setEmail(updatedUserDTO.getEmail());
            userDTO.setRole(updatedUserDTO.getRole());
            userDTO.setPassword(updatedUserDTO.getPassword());
            return service.save(userDTO);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    /**
     * Delete a User
     *
     * This endpoint allows the admin to delete a user profile by its unique ID.
     *
     * @param id The unique identifier of the user to delete.
     * @return ResponseEntity indicating the result of the delete operation.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Optional<UserDTO> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            service.delete(userOptional.get());
            return ResponseEntity.ok("User deleted successfully"); // Return a 200 OK response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        }
    }
}
