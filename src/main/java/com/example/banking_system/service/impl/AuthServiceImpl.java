package com.example.banking_system.service.impl;

import com.example.banking_system.dto.request.LoginRequest;
import com.example.banking_system.dto.request.RegisterRequest;
import com.example.banking_system.dto.response.AuthResponse;

public interface AuthServiceImpl {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
