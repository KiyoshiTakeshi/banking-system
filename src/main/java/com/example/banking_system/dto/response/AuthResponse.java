package com.example.banking_system.dto.response;

import lombok.Data;

@Data
public class AuthResponse {

    private String token;

    private String type;

    private String username;

    private String email;
}
