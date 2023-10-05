package com.example.webshopbackend.service;

import com.example.webshopbackend.model.Original;
import com.example.webshopbackend.model.User;
import com.example.webshopbackend.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO userDTO);
    UserDTO findByEmail(String email);
    List<UserDTO> findAll();
    Optional<UserDTO> findById(Long id);
    void delete(UserDTO userDTO);


/*    User save(User user);
    User findByEmail(String email);
    List<User> findAll();
    Optional<User> findById(Long id);
    void delete(User user);*/
}
