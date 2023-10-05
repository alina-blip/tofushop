package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.OriginalDTO;
import com.example.webshopbackend.model.Category;
import com.example.webshopbackend.model.Original;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.repository.OriginalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class OriginalServiceImpl implements OriginalService {
    private final OriginalRepository repository;

    @Autowired
    OriginalServiceImpl(OriginalRepository repository) {
        this.repository = repository;
    }

    @Override
    public OriginalDTO save(OriginalDTO originalDTO) {
        Original original = convertToEntity(originalDTO);
        Original savedOriginal = repository.save(original);
        return convertToDTO(savedOriginal);
    }

    public List<OriginalDTO> findAll() {
        List<Original> originals = repository.findAll();
        return originals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OriginalDTO> findById(Long id) {
        Optional<Original> original = repository.findById(id);
        return original.map(this::convertToDTO);
    }
    @Override
    public void delete(OriginalDTO originalDTO) {
        Original original = convertToEntity(originalDTO);
        repository.delete(original);
    }


    private Original convertToEntity(OriginalDTO originalDTO) {
        Original original = new Original();
        original.setId(originalDTO.getId());
        original.setTitle(originalDTO.getTitle());
        original.setSize(originalDTO.getSize());
        original.setMaterial(originalDTO.getMaterial());
        original.setDescription(originalDTO.getDescription());
        original.setPrice(originalDTO.getPrice());
        original.setUrl(originalDTO.getUrl());
        original.setCategory(Category.valueOf(originalDTO.getCategory()));
        original.setQuantity(originalDTO.getQuantity());
        return original;
    }

    private OriginalDTO convertToDTO(Original original) {
        OriginalDTO originalDTO = new OriginalDTO();
        originalDTO.setId(original.getId());
        originalDTO.setTitle(original.getTitle());
        originalDTO.setSize(original.getSize());
        originalDTO.setMaterial(original.getMaterial());
        originalDTO.setDescription(original.getDescription());
        originalDTO.setPrice((float) original.getPrice());
        originalDTO.setUrl(original.getUrl());
        originalDTO.setCategory(original.getCategory().toString());
        originalDTO.setQuantity(original.getQuantity());
        return originalDTO;
    }


}
