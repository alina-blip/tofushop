package com.example.webshopbackend.repository;
import com.example.webshopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();
    User findByEmail(String email);
}
