package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Add this annotation
@AutoConfigureMockMvc
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);

    }

    @Test
    @Transactional
    void testSaveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John");
        userDTO.setRole(UserRole.USER);
        userDTO.setSurname("Doem");
        userDTO.setPassword("12345");
        userDTO.setCountry("Graz");
        userDTO.setEmail("John@Doem.com");
        userDTO.setHousenumber("1A");
        userDTO.setStreet("Getreidegasse");
        userDTO.setPostalcode("1030");

        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSurname("Doem");
        user.setRole(UserRole.USER);
        user.setPassword("12345");
        user.setCountry("Graz");
        user.setEmail("John@Doem.com");
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

    @Transactional
    void testFindAllUsers() {
        // Create a list of User entities for mocking the repository response
        List<User> userList = new ArrayList<>();
        userList.add(createUser(2L, "John", "Doe", UserRole.USER, "12345", "Graz", "John@Doe.com", "1A", "Getreidegasse", "1030"));
        userList.add(createUser(3L, "Alice", "Smith", UserRole.ADMIN, "password", "Vienna", "alice@example.com", "2B", "Main St", "2000"));

        // Mock the repository's findAll method to return the list of users
        when(userRepository.findAll()).thenReturn(userList);

        // Call the findAll method in your service
        List<UserDTO> usersDTOList = userService.findAll();

        // Ensure that the list of UserDTOs matches the list of mocked User entities
        assertEquals(userList.size(), usersDTOList.size());

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDTO userDTO = usersDTOList.get(i);

            assertEquals(user.getId(), userDTO.getId());
            assertEquals(user.getName(), userDTO.getName());
            assertEquals(user.getSurname(), userDTO.getSurname());
            assertEquals(UserRole.valueOf(user.getRole().toString()), userDTO.getRole());
            assertEquals(user.getPassword(), userDTO.getPassword());
            assertEquals(user.getCountry(), userDTO.getCountry());
            assertEquals(user.getEmail(), userDTO.getEmail());
            assertEquals(user.getHousenumber(), userDTO.getHousenumber());
            assertEquals(user.getStreet(), userDTO.getStreet());
            assertEquals(user.getPostalcode(), userDTO.getPostalcode());
        }
    }

    private User createUser(Long id, String name, String surname, UserRole role, String password, String country, String email, String housenumber, String street, String postalcode) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(UserRole.USER);
        user.setPassword(password);
        user.setCountry(country);
        user.setEmail(email);
        user.setHousenumber(housenumber);
        user.setStreet(street);
        user.setPostalcode(postalcode);
        return user;
    }

}
