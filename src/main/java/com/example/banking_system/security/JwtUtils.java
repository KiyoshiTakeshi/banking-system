package com.example.banking_system.security;

import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component

public class JwtUtils {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    // Khởi tạo token
    private String generatetoken (UserDetails userDetails){}


    private String extractUsername (String token) {}
    private boolean isTokenValid (String token, UserDetails userDetails) {}
}
