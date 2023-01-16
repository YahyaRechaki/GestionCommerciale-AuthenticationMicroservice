package com.example.authentication_service.controllers;

import com.example.authentication_service.models.User;
import com.example.authentication_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    // To avoid Null errors I've modified the register method to return ResponseEntity, and to check if the return value from createUser is not null.
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.createUser(user);
        if(newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newUser, HttpStatus.OK);
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
