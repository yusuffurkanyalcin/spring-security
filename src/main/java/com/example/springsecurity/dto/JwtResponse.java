package com.example.springsecurity.dto;

public record JwtResponse (
        String accessToken,
        String refreshToken
){
}
