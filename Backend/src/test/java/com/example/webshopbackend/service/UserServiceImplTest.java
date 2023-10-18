package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testSaveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John");
        userDTO.setRole(UserRole.USER);
        userDTO.setSurname("Doe");
        userDTO.setPassword("12345");
        userDTO.setCountry("Graz");
        userDTO.setEmail("John@Doe.com");
        userDTO.setHousenumber("1A");
        userDTO.setStreet("Getreidegasse");
        userDTO.setPostalcode("1030");

        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSurname("Doe");
        user.setRole(UserRole.USER);
        user.setPassword("12345");
        user.setCountry("Graz");
        user.setEmail("John@Doe.com");
        user.setHousenumber("1A");
        user.setStreet("Getreidegasse");
        user.setPostalcode("1030");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDTO savedUserDTO = userService.save(userDTO);

        assertEquals(userDTO.getId(), savedUserDTO.getId());
        assertEquals(userDTO.getName(), savedUserDTO.getName());
        assertEquals(userDTO.getEmail(), savedUserDTO.getEmail());
        assertEquals(userDTO.getRole(),savedUserDTO.getRole());
        assertEquals(user.getPassword(), savedUserDTO.getPassword());
        assertEquals(userDTO.getCountry(), savedUserDTO.getCountry());
        assertEquals(userDTO.getHousenumber(), savedUserDTO.getHousenumber());
        assertEquals(userDTO.getStreet(), savedUserDTO.getStreet());
        assertEquals(userDTO.getPostalcode(), savedUserDTO.getPostalcode());
    }
}
