package com.example.webshopbackend.service;

import com.example.webshopbackend.exception.StorageException;
import com.example.webshopbackend.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void store(MultipartFile file) throws StorageException;

    Resource loadAsResource(String filename) throws StorageFileNotFoundException;

    void delete(String filename) throws StorageFileNotFoundException;
}

