package com.example.springsecurity.controller;

import com.example.springsecurity.dto.CreateUserRequest;
import com.example.springsecurity.model.AuthRequest;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.AuthServiceIF;
import com.example.springsecurity.service.UserDetailsServiceIF;
import com.example.springsecurity.service.jwt.JWTServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceIF userService;
    private final AuthServiceIF authService;

    @GetMapping("/welcome")
    public String welcome() {
        return "WELCOME";
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is user endpoint";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "This is admin endpoint";
    }

    @PostMapping("/createUser")
    public User addUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        return authService.authenticateAndGenerateToken(request);
    }
}
