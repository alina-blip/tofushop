package com.example.webshopbackend.service;

import com.example.webshopbackend.exception.StorageException;
import com.example.webshopbackend.exception.StorageFileNotFoundException;
import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path storageDirectory;

    private final ImageRepository repository;

    @Autowired
    public StorageServiceImpl(ImageRepository repository) {
        this.repository = repository;
        this.storageDirectory = Paths.get("../Backend/uploads");
        init();
    }

    private void init() {
        try {
            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public List<Image> findAll() {
        List<Image> images = repository.findAll();
        return images;
    }

    @Override
    public void store(MultipartFile file) throws StorageException {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            Path targetLocation = this.storageDirectory.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageFileNotFoundException {
        try {
            Path file = storageDirectory.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read the file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read the file: " + filename, e);
        }
    }

    @Override
    public void delete(String filename) throws StorageFileNotFoundException {
        try {
            Path file = storageDirectory.resolve(filename);
            if (Files.exists(file)) {
                Files.delete(file);
            } else {
                throw new StorageFileNotFoundException("Could not delete the file: " + filename);
            }
        } catch (IOException e) {
            throw new StorageException("Could not delete the file: " + filename, e);
        }
    }
}

