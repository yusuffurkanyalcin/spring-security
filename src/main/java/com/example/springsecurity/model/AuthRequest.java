package com.example.springsecurity.model;

public record AuthRequest(
        String username,
        String password
) {
}
