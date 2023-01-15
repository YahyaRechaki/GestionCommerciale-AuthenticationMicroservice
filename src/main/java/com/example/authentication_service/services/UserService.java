package com.example.authentication_service.services;

import com.example.authentication_service.configurations.SecurityConfig;
import com.example.authentication_service.models.User;
import com.example.authentication_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public User createUser(User user) {
        user.setPassword(securityConfig.bCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return Optional.empty();
        }
        boolean passwordMatches = securityConfig.bCryptPasswordEncoder().matches(password, user.getPassword());
        if (passwordMatches) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }


}
