package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.exception.StorageException;
import com.example.webshopbackend.exception.StorageFileNotFoundException;
import com.example.webshopbackend.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StorageService {

    List<Image> findAll();

    Optional<Image> findById(Long id);


    void store(MultipartFile file) throws StorageException;

    Resource loadAsResource(String filename) throws StorageFileNotFoundException;

    void delete(String filename) throws StorageFileNotFoundException;
}

