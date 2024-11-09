package com.example.springsecurity.service.jwt;

import com.example.springsecurity.model.RefreshToken;
import com.example.springsecurity.repository.RefreshTokenRepository;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final String REFRESH_TOKEN_EXCEPTION_MESSAGE = "Refresh token was expired. Please make  a new login request";

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    // şuandan itibaren 10 dakikalık bir resfresh token
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000)) // 10 dakika
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);

            StringBuilder sb = new StringBuilder();
            sb.append(token.getToken());
            sb.append(" ");
            sb.append(REFRESH_TOKEN_EXCEPTION_MESSAGE);
            throw new RuntimeException(sb.toString());
        }
        return token;
    }
}
