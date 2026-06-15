package com.jiralite.backend.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.jiralite.backend.entity.User;

import io.jsonwebtoken.ExpiredJwtException;


public class JwtServiceTest {
    
    private JwtService jwtService;
    private User user;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "test-secret-key-min-32-chars-for-hmac-sha256");
        ReflectionTestUtils.setField(jwtService, "expiration", 86400000L);

        user = new User();
        ReflectionTestUtils.setField(user, "id", UUID.randomUUID());
        user.setEmail("test@mail.com");
    }

    @Test
    void shouldGenerateToken() {
        String token = jwtService.generateToken(user);
        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    void shouldExtractUserIdFromToken() {
        String token = jwtService.generateToken(user);
        UUID extractedId = jwtService.extractUserId(token);
        assertThat(extractedId).isEqualTo(user.getId());
    }

    @Test
    void shouldRejectExpiredToken() {
        ReflectionTestUtils.setField(jwtService, "expiration", -1000L);
        String token = jwtService.generateToken(user);
        assertThatThrownBy(() -> jwtService.extractUserId(token)).isInstanceOf(ExpiredJwtException.class);
    }

}
