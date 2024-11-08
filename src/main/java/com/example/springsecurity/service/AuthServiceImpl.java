package com.example.springsecurity.service;

import com.example.springsecurity.model.AuthRequest;
import com.example.springsecurity.service.jwt.JWTServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthServiceIF {

    private final UserDetailsServiceIF userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceIF jwtService;


    @Override
    public String authenticateAndGenerateToken(AuthRequest request) {
        Authentication authentication1 =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication1.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        throw new UsernameNotFoundException("Invalid username {} " + request.username());
    }
}
