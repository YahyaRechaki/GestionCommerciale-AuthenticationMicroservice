package com.example.authentication_service.repositories;

import com.example.authentication_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
