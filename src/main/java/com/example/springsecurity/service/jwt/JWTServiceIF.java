package com.example.springsecurity.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JWTServiceIF extends TokenServiceIF {
    String generateToken(String username);
    boolean validateToken(String token, UserDetails userDetails);
    String extractUsername(String token);
    Date extractExpiration(String token);
}
