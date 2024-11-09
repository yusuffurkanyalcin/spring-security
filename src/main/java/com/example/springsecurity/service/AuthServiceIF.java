package com.example.springsecurity.service;

import com.example.springsecurity.dto.JwtResponse;
import com.example.springsecurity.model.AuthRequest;

public interface AuthServiceIF {

    JwtResponse authenticateAndGenerateToken(AuthRequest request);
}
