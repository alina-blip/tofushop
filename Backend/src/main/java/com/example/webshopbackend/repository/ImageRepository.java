package com.example.webshopbackend.repository;

import com.example.webshopbackend.model.Image;
import com.example.webshopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository <Image, Integer>{
    @Override
    List<Image> findAll();
}
