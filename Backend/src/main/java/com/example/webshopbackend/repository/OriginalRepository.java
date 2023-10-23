package com.example.webshopbackend.repository;

import com.example.webshopbackend.model.Original;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * OriginalRepository Interface
 *
 * This repository interface provides methods for performing CRUD (Create, Read, Update, Delete) operations on Original entities in the database.
 */
@Repository
public interface OriginalRepository extends JpaRepository<Original, Long> {

    /**
     * Custom method to retrieve a list of all Original entities.
     *
     * @return List of Original entities.
     */
    List<Original> findAll();
}
