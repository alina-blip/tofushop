package com.example.webshopbackend.repository;

import com.example.webshopbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CartRepository Interface
 *
 * This repository interface provides methods for performing CRUD (Create, Read, Update, Delete) operations on Cart entities in the database.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}

