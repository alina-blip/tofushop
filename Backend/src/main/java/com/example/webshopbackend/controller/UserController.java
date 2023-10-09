package com.example.webshopbackend.controller;
import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.model.UserRole;
import com.example.webshopbackend.security.JwtIssuer;
import com.example.webshopbackend.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final JwtIssuer jwtIssuer;
    private final UserService service;
    @Autowired
    UserController(JwtIssuer jwtIssuer, UserService service) {
        this.jwtIssuer = jwtIssuer;
        this.service = service;
    }

    @GetMapping("")
    public List<UserDTO> all() {
        List<UserDTO> userDTOs = service.findAll();
        return userDTOs;
    }

    @PostMapping("")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        userDTO.setRole(UserRole.USER);
        return service.save(userDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
        UserDTO existingUser = service.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            // Compare the provided login password with the stored hashed password
            if (BCrypt.checkpw(userDTO.getPassword(), existingUser.getPassword())) {

                var token = jwtIssuer.issue(1, userDTO.getEmail(), List.of("USER"));
                return ResponseEntity.ok(LoginResponse.builder()
                        .accessToken(token)
                        .build());

            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse.builder()
                .build());
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable long id, @RequestBody UserDTO updatedUserDTO) {
        Optional<UserDTO> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            UserDTO userDTO = userOptional.get();
            userDTO.setName(updatedUserDTO.getName());
            userDTO.setSurname(updatedUserDTO.getSurname());
            userDTO.setStreet(updatedUserDTO.getStreet());
            userDTO.setHousenumber(updatedUserDTO.getHousenumber());
            userDTO.setPostalcode(updatedUserDTO.getPostalcode());
            userDTO.setCountry(updatedUserDTO.getCountry());
            userDTO.setEmail(updatedUserDTO.getEmail());
            userDTO.setRole(updatedUserDTO.getRole());
            userDTO.setPassword(updatedUserDTO.getPassword());
            return service.save(userDTO);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Optional<UserDTO> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            service.delete(userOptional.get());
            return ResponseEntity.ok("User deleted successfully"); // Return a 200 OK response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        }
    }




    /*private final UserService service;
    @Autowired
    UserController(UserService service) {
        this.service = service;
    }
    @GetMapping("")
    public List<User> all() {
        return service.findAll();
    }
*/
   /* @PostMapping("")
    public User save(@RequestBody User user) {
        return service.save(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = service.findByEmail(user.getEmail());
        if (existingUser != null) {
            // Compare the provided login password with the stored hashed password
            if (BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
                return ResponseEntity.ok("{\"message\": \"Login successful\"}");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid credentials\"}");
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody User updatedUser) {
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setStreet(updatedUser.getStreet());
            user.setHousenumber(updatedUser.getHousenumber());
            user.setPostalcode(updatedUser.getPostalcode());
            user.setCountry(updatedUser.getCountry());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            user.setPassword(updatedUser.getPassword());
            return service.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }*/

   /* @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            service.delete(userOptional.get());
            return ResponseEntity.ok("User deleted successfully"); // Return a 200 OK response
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
        }
    }*/

}
