package com.jiralite.backend.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jiralite.backend.dto.auth.AuthResponse;
import com.jiralite.backend.dto.auth.LoginRequest;
import com.jiralite.backend.dto.auth.RegisterRequest;
import com.jiralite.backend.entity.User;
import com.jiralite.backend.exception.EmailAlreadyUsedException;
import com.jiralite.backend.exception.UserNotFoundException;
import com.jiralite.backend.repository.UserRepository;
import com.jiralite.backend.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyUsedException(request.email());
        }

        User user = new User();
        user.setEmail(request.email());
        user.setFirstname(request.firstname());
        user.setLastname(request.lastname());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        return buildAuthResponse(user);

    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        User user = userRepository.findByEmail(loginRequest.email())
            .orElseThrow(() -> new UserNotFoundException(loginRequest.email()));
        
        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(User user) {
        return new AuthResponse(
            jwtService.generateToken(user),
            user.getFirstname(),
            user.getLastname(),
            user.getEmail()
        );
    }

}
