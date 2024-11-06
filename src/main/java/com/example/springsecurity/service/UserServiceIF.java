package com.example.springsecurity.service;

import com.example.springsecurity.dto.CreateUserRequest;
import com.example.springsecurity.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserServiceIF extends UserDetailsService {
    Optional<User> getByUsername(String username);
    User createUser(CreateUserRequest createUserRequest);
}
