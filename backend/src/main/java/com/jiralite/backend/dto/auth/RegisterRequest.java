package com.jiralite.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank String lastname,
    @NotBlank String firstname,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 8) String password
) {}
