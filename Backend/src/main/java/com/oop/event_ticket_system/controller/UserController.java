package com.oop.event_ticket_system.controller;

import com.oop.event_ticket_system.resources.RegisterUserResource;
import com.oop.event_ticket_system.resources.UserLoginResours;
import com.oop.event_ticket_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:65230")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserResource registerUserResource) {
        try {
            String result = userService.registerUser(registerUserResource);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginResours userLoginResours) {
        try {
            return ResponseEntity.ok(userService.login(userLoginResours));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}