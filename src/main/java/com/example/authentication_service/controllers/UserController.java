package com.example.authentication_service.controllers;

import java.util.Random;
import com.example.authentication_service.models.User;
import com.example.authentication_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
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
    //@PostMapping("/register")
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        int code = (int) (Math.random() * 9000) + 1000;
        user.setVerification_code(code);
        User newUser = userService.createUser(user);
        if(newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    //@PostMapping("/login")
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public Optional<User> login(@RequestBody User user) {
        return userService.authenticateUser(user.getEmail(),user.getPassword());
    }

    @RequestMapping(method= RequestMethod.GET, path="/get-verification-code")
    public ResponseEntity<Integer> getCode(@RequestBody String email){
        try {
            int code = userService.getCode(email);
            return new ResponseEntity<>(code, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Hi Yahya!!!";
    }
}
