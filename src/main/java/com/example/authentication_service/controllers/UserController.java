package com.example.authentication_service.controllers;

import com.example.authentication_service.models.User;
import com.example.authentication_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestBody User user) {
        return userService.authenticateUser(user.getEmail(),user.getPassword());
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Hi Yahya!!!";
    }
}
