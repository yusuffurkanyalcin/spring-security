package com.example.springsecurity.service;

import com.example.springsecurity.dto.CreateUserRequest;
import com.example.springsecurity.model.User;

import java.util.Optional;

public interface UserServiceIF {
    Optional<User> getByUsername(String username);
    User createUser(CreateUserRequest createUserRequest);
}
