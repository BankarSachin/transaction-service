package com.smartbank.transactionservice.mapper;

import java.util.function.Function;

import com.smartbank.transactionservice.dto.TransactionRequest;
import com.smartbank.transactionservice.dto.TransactionResponse;
import com.smartbank.transactionservice.entity.Transaction;
import com.smartbank.transactionservice.entity.TransactionEntry;
import com.smartbank.transactionservice.enums.TransactionStatus;

public class TransactionMapper {

	public static Transaction toEntity(TransactionRequest transactionRequest) {
		return Transaction.builder()
				.transactionDate(transactionRequest.getTransactionDate())
				.transactionStatus(TransactionStatus.SUCCESS)
				.transactionSummary(transactionRequest.getTransactionSummary())
				.build();
	}

	public static TransactionResponse toResponse(Transaction transaction, TransactionEntry transactionEntry) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setUtrNumber(transaction.getUtrNumber());
		transactionResponse.setTransactionType(transactionEntry.getTransactionType());
		transactionResponse.setTransactionStatus(transaction.getTransactionStatus());
		transactionResponse.setTransactionDate(transaction.getTransactionDate());
		transactionResponse.setTransactionAmount(transactionEntry.getTransactionAmount());
		transactionResponse.setTransactionAccount(transactionEntry.getAccountNumber());
		transactionResponse.setClosingBalance(transactionEntry.getClosingBalance());
		transactionResponse.setTransactionDescription(transaction.getTransactionSummary());
		return transactionResponse;
	}

	public static final Function<Transaction, TransactionResponse> txnResponseMapper = t -> {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setUtrNumber(t.getUtrNumber());
		transactionResponse.setTransactionDate(t.getTransactionDate());
		transactionResponse.setTransactionDescription(t.getTransactionSummary());
		TransactionEntry transactionEntry = t.getTransactionEntries().get(0);
		transactionResponse.setTransactionType(transactionEntry.getTransactionType());
		transactionResponse.setTransactionAccount(transactionEntry.getAccountNumber());
		transactionResponse.setTransactionAmount(transactionEntry.getTransactionAmount());
		transactionResponse.setClosingBalance(transactionEntry.getClosingBalance());
		transactionResponse.setTransactionStatus(t.getTransactionStatus());
		transactionResponse.setTransactionDescription(t.getTransactionSummary());
		return transactionResponse;
	};
}
