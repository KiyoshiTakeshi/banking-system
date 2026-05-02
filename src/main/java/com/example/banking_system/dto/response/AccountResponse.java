package com.example.banking_system.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponse {

    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    private String ownerUsername;

    private LocalDateTime createdAt;
}
