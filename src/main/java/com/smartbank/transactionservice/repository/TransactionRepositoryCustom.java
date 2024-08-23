package com.smartbank.transactionservice.repository;

import java.time.LocalDate;
import java.util.List;

import com.smartbank.transactionservice.entity.Transaction;
import com.smartbank.transactionservice.enums.TransactionType;

public interface TransactionRepositoryCustom {
    List<Transaction> findTransactionsByCriteria(String accountNumber, LocalDate startDate, LocalDate endDate, TransactionType transactionType);
}