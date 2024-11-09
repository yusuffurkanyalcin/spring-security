package com.example.springsecurity.controller;

import com.example.springsecurity.dto.CreateUserRequest;
import com.example.springsecurity.dto.JwtResponse;
import com.example.springsecurity.dto.RefreshTokenRequest;
import com.example.springsecurity.model.AuthRequest;
import com.example.springsecurity.model.RefreshToken;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.AuthServiceIF;
import com.example.springsecurity.service.UserDetailsServiceIF;
import com.example.springsecurity.service.jwt.JWTServiceIF;
import com.example.springsecurity.service.jwt.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceIF userService;
    private final AuthServiceIF authService;
    private final RefreshTokenService refreshTokenService;
    private final JWTServiceIF jwtService;

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
    public JwtResponse generateToken(@RequestBody AuthRequest request) {
        return authService.authenticateAndGenerateToken(request);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.token();
        return refreshTokenService.findByToken(request.token())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getUsername());
                    return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
                }).orElseThrow(() -> new RuntimeException("Refresh token does not exist in the database."));
    }

}
