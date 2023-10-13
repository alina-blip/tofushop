package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.OriginalDTO;
import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.Category;
import com.example.webshopbackend.model.Original;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.repository.OriginalRepository;
import com.example.webshopbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OriginalServiceImplTest {

    @Mock
    private OriginalRepository originalRepository;
    private OriginalService originalService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        originalService = new OriginalServiceImpl(originalRepository);
    }

    @Test
    void testSaveOriginal() {
        OriginalDTO originalDTO = new OriginalDTO();
        originalDTO.setId(1);
        originalDTO.setCategory(String.valueOf(Category.ORIGINAL));
        originalDTO.setDescription("A description");
        originalDTO.setMaterial("Cotton");
        originalDTO.setPrice(800);
        originalDTO.setImageId(1);
        originalDTO.setSize("30x40cm");
        originalDTO.setQuantity(1);
        originalDTO.setTitle("OriginalTitle");
        originalDTO.setUrl("url");

        Original original = new Original();
        original.setId(originalDTO.getId());
        original.setImageId(originalDTO.getImageId());
        original.setCategory(Category.valueOf(originalDTO.getCategory()));
        original.setDescription(originalDTO.getDescription());
        original.setMaterial(originalDTO.getMaterial());
        original.setPrice(originalDTO.getPrice());
        original.setSize(originalDTO.getSize());
        original.setQuantity(originalDTO.getQuantity());
        original.setTitle(originalDTO.getTitle());
        original.setUrl(originalDTO.getUrl());


        when(originalRepository.save(Mockito.any(Original.class))).thenReturn(original);

        OriginalDTO savedOriginalDTO = originalService.save(originalDTO);

        assertEquals(originalDTO.getId(), savedOriginalDTO.getId());
        assertEquals(originalDTO.getImageId(), savedOriginalDTO.getImageId());
        assertEquals(originalDTO.getCategory(), savedOriginalDTO.getCategory());
        assertEquals(originalDTO.getDescription(), savedOriginalDTO.getDescription());
        assertEquals(originalDTO.getMaterial(), savedOriginalDTO.getMaterial());
        assertEquals(originalDTO.getPrice(), savedOriginalDTO.getPrice());
        assertEquals(originalDTO.getSize(), savedOriginalDTO.getSize());
        assertEquals(originalDTO.getQuantity(), savedOriginalDTO.getQuantity());
        assertEquals(originalDTO.getTitle(), savedOriginalDTO.getTitle());
        assertEquals(originalDTO.getUrl(), savedOriginalDTO.getUrl());

    }

}
