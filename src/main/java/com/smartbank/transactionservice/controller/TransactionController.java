package com.smartbank.transactionservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartbank.transactionservice.dto.TransactionRequest;
import com.smartbank.transactionservice.dto.TransactionResponse;
import com.smartbank.transactionservice.dto.TransferRequest;
import com.smartbank.transactionservice.enums.TransactionType;
import com.smartbank.transactionservice.exception.TxnException;
import com.smartbank.transactionservice.service.TransactionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/v1/")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	/**
	 * Make entry of transaction in Transaction DB
	 * specifically used by deposit and withdrawal service
	 * @param transactionRequest
	 * @return
	 * @throws TxnException
	 */
	@PostMapping(value="/transactions/{accountnumber}/entry",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> entry(final @PathVariable( value = "accountnumber",required = true) String accountNumber,
													 final @Valid @RequestBody TransactionRequest transactionRequest) throws TxnException{
		final TransactionResponse txnResponse =  transactionService.entry(accountNumber,transactionRequest);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(txnResponse);
	}
	
	
	/**
	 * Gets Transaction history for given account
	 * @param accountNumber account number
	 * @param startDate start date to check txn
	 * @param endDate end date to search txn
	 * @param transactionType credit,debit,transfer
	 * @return
	 * @throws TxnException
	 */
	@GetMapping(value="/transactions/{accountnumber}/history",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionResponse>> history (
			final @PathVariable(value ="accountnumber",required = true) String accountNumber,
	        @RequestParam(value = "startDate", required = false) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) LocalDate endDate,
	        @RequestParam(value = "transactionType", required = false) TransactionType transactionType
			) throws TxnException{
		final List<TransactionResponse> txnResponse =  transactionService.getTxHistory(accountNumber, 
																					   startDate, 
																					   endDate, 
																					   transactionType
																					  );
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(txnResponse);
	}
	
	
	@PostMapping(value="/transactions/transfer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> transfer(final @Valid @RequestBody TransferRequest transferRequest) throws TxnException{
		final TransactionResponse transactionResponse = transactionService.performTransfer(transferRequest);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(transactionResponse);
	}
}
