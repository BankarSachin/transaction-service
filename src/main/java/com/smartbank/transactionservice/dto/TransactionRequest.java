package com.smartbank.transactionservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionRequest {
	private BigDecimal transactionAmount;
	private String transactionSummary;
}
