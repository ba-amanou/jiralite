package com.jiralite.backend.dto.auth;

public record AuthResponse(
    String token,
    String firtsname,
    String lastname,
    String email
) {}
