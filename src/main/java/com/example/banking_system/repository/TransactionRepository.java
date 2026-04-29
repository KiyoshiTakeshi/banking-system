package com.example.banking_system.repository;

import com.example.banking_system.entity.Account;
import com.example.banking_system.entity.Transaction;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount = :account OR t.targetAccount = :account")
    List<Transaction> findAllByAccount(@Param("account") Account account);

    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount = :account")
    List<Transaction> findSentTransactionByAccount(@Param("account") Account account);

    @Query("SELECT t FROM Transaction t WHERE t.targetAccount = :account")
    List<Transaction> findReceivedTransactionByAccount(@Param("account") Account account);

    @Query("SELECT t FROM Transaction t WHERE (t.sourceAccount = :account OR t.targetAccount = :account) AND (t.createdAt BETWEEN :startDate AND :endDate) ")
    List<Transaction> findAllByAccountWithDate(
            @Param("account") Account account,
            @Param("startDate")LocalDateTime startDate,
            @Param("endDate")LocalDateTime endDate
    );
}
