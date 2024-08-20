package com.smartbank.transactionservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private UUID transactionId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", nullable = false)
	private String accountNumber;

	@Column(name = "transaction_type", nullable = false, length = 15)
	private String transactionType;

	@Column(name = "transaction_amount", nullable = false, precision = 10, scale = 2)
	private BigDecimal transactionAmount;

	@Column(name = "transaction_status", nullable = false, length = 15)
	private String transactionStatus;

	@Column(name = "closing_balance", nullable = false, precision = 10, scale = 2)
	private BigDecimal closingBalance;

	@CreationTimestamp
	@Column(name = "transaction_date", nullable = false)
	private LocalDateTime transactionDate;

}
