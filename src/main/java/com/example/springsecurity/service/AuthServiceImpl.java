package com.example.springsecurity.service;

import com.example.springsecurity.dto.JwtResponse;
import com.example.springsecurity.model.AuthRequest;
import com.example.springsecurity.model.RefreshToken;
import com.example.springsecurity.service.jwt.JWTServiceIF;
import com.example.springsecurity.service.jwt.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;


    @Override
    public JwtResponse authenticateAndGenerateToken(AuthRequest request) {
        Authentication authentication1 =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication1.isAuthenticated()) {
            String accessToken = jwtService.generateToken(request.username());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.username());
            return new JwtResponse(accessToken, refreshToken.getToken());
        }
        throw new UsernameNotFoundException("Invalid username {} " + request.username());
    }
}
