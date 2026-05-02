package com.example.banking_system.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {

    private Long id;

    private String type;

    private BigDecimal amount;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;

    private String description;

    private String sourceAccountNumber;

    private String targetAccountNumber;

    private LocalDateTime createdAt;
}
