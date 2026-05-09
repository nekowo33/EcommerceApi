package com.ws101.novio.EcommerceApi.repository;

import com.ws101.novio.EcommerceApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // Custom method to find a user by their username during login
    Optional<User> findByUsername(String username);
}