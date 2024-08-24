package com.smartbank.transactionservice.service.external;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.smartbank.transactionservice.dto.NotificationRequest;
import com.smartbank.transactionservice.dto.NotificationResponse;
import com.smartbank.transactionservice.exception.TxnException;

/**
 * Sends mail
 * @author Sachin
 */
public interface NotificationServiceClient {

	/**
	 * Sends Trasfer completion mail to Payer and Payee Account holder.
	 * Authentication Token is used if from Payer Authentication because he is the owner of the transaction 
	 * @param headers
	 * @param debitAccountNumber
	 * @param notificationRequest
	 * @return
	 * @throws TxnException
	 */
	public CompletableFuture<NotificationResponse> notifyTransfer(Map<String,String> headers, String debitAccountNumber,NotificationRequest notificationRequest);
}
