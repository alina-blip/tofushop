package com.example.webshopbackend.service;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.Original;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


    @Service
    public class UserServiceImpl implements UserService {
        private final UserRepository repository;

        @Autowired
        public UserServiceImpl(UserRepository repository) {
            this.repository = repository;
        }

        @Override
        public UserDTO save(UserDTO userDTO) {
            String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
            userDTO.setPassword(hashedPassword);

            User user = convertToEntity(userDTO);
            User savedUser = repository.save(user);

            return convertToDTO(savedUser);
        }

        @Override
        public List<UserDTO> findAll() {
            List<User> users = repository.findAll();
            return users.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        @Override
        public UserDTO findByEmail(String email) {
            User user = repository.findByEmail(email);
            return (user != null) ? convertToDTO(user) : null;
        }

        @Override
        public Optional<UserDTO> findById(Long id) {
            Optional<User> user = repository.findById(id);
            return user.map(this::convertToDTO);
        }

        @Override
        public void delete(UserDTO userDTO) {
            User user = convertToEntity(userDTO);
            repository.delete(user);
        }

        // Helper methods to convert between UserDTO and User
        private UserDTO convertToDTO(User user) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setSurname(user.getSurname());
            userDTO.setStreet(user.getStreet());
            userDTO.setHousenumber(user.getHousenumber());
            userDTO.setPostalcode(user.getPostalcode());
            userDTO.setCountry(user.getCountry());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole()); // Assuming role is an enum
            userDTO.setPassword(user.getPassword());
            return userDTO;
        }

        private User convertToEntity(UserDTO userDTO) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setStreet(userDTO.getStreet());
            user.setHousenumber(userDTO.getHousenumber());
            user.setPostalcode(userDTO.getPostalcode());
            user.setCountry(userDTO.getCountry());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setRole(UserRole.valueOf(userDTO.getRole().toString())); // Assuming role is an enum
            return user;
        }




/*private final UserRepository repository;
    @Autowired
    UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return repository.save(user);
    }
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }*/



}
