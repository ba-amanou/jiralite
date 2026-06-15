package com.jiralite.backend.dto.error;

import java.time.LocalDateTime;

public record ErrorResponse( 
    int status,
    String error,
    String message,
    LocalDateTime timestamp
) {}
