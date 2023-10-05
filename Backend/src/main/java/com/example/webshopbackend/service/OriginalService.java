package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.OriginalDTO;
import com.example.webshopbackend.model.Original;

import java.util.List;
import java.util.Optional;

public interface OriginalService {

    OriginalDTO save(OriginalDTO originalDTO);
    List<OriginalDTO> findAll();
    Optional<OriginalDTO> findById(Long id);
    void delete(OriginalDTO originalDTO);
}
