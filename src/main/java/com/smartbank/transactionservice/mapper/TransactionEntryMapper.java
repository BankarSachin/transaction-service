package com.smartbank.transactionservice.mapper;

import com.smartbank.transactionservice.dto.TransactionRequest;
import com.smartbank.transactionservice.entity.Transaction;
import com.smartbank.transactionservice.entity.TransactionEntry;

public class TransactionEntryMapper {
	

	public static TransactionEntry toEntity(Transaction transaction,TransactionRequest transactionRequest, String accountNumber) {
		TransactionEntry transactionEntry = new TransactionEntry();
		transactionEntry.setUtrNumber(transaction.getUtrNumber());	
		transactionEntry.setTransactionType(transactionRequest.getTransactionType());
		transactionEntry.setTransactionAmount(transactionRequest.getTransactionAmount());
		transactionEntry.setAccountNumber(accountNumber);	
		transactionEntry.setClosingBalance(transactionRequest.getClosingBalance());
		transactionEntry.setTransaction(transaction);
		return transactionEntry;
	}
}
