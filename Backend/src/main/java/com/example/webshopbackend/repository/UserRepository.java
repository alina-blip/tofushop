package com.example.webshopbackend.repository;
import com.example.webshopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
/**
 * UserRepository Interface
 *
 * This repository interface provides methods for performing CRUD (Create, Read, Update, Delete) operations on User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom method to retrieve a list of all User entities.
     *
     * @return List of User entities.
     */
    List<User> findAll();

    /**
     * Custom method to find a user by their email.
     *
     * @param email The email of the user to find.
     * @return The User entity if found, or null if not found.
     */
    User findByEmail(String email);
}

