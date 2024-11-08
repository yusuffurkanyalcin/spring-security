package com.example.springsecurity.service;

import com.example.springsecurity.model.AuthRequest;

public interface AuthServiceIF {

    String authenticateAndGenerateToken(AuthRequest request);
}
