package com.jiralite.backend.config;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jiralite.backend.entity.User;
import com.jiralite.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class AuditorAwareConfig {

    private final UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
                return Optional.empty();
            }
            String userId = auth.getName();
            return userRepository.findById(UUID.fromString(userId));
        };
    }
}