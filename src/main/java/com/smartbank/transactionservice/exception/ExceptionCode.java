package com.smartbank.transactionservice.exception;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Creating predefined exceptioncodes which helps addressing backend exceptions in UI
 * @author Sachin
 */
@Getter
public class ExceptionCode implements Serializable{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 6905584233478237530L;
	private static Map<String, ExceptionCode> exceptionCodes = new HashMap<>();
	
	
	private String id;  //ACCS001
	private String key; //ACCS_INVALID_INPUT
	private String message;
	private HttpStatus httpStatus;
	
	
	public ExceptionCode(String id, String key, String message,HttpStatus httpStatus) {
		super();
		this.id = id;
		this.key = key;
		this.message = message;
		this.httpStatus = httpStatus;
		exceptionCodes.put(key, this);
	}
	
	@Override
	public String toString() {
		return this.message;
	}
	

	/**
	 * Formats exception based on input
	 * Suppose we define message as "invalid input {1}" and send argument [email]
	 * After message format final exception message would be "invalid input email" 
	 * @param args array of values
	 * @return formatted mexception message
	 */
	public String toString(String[] args) {
		String formattedString = this.message;
		if ( args!=null && args.length!=0) {
			try {
				MessageFormat messageFormat = new MessageFormat(message);
				formattedString = messageFormat.format(args);
			} catch (Exception e) {
				//do nothing
			}
		}
		return formattedString;
	}
	
	public static ExceptionCode getExceptionCode(String key) {
		return exceptionCodes.getOrDefault(key, ExceptionCode.ACCS_UNKNOWN_EXCEPTION);
	}
	
	//Server Error Series
	public static final ExceptionCode ACCS_UNKNOWN_EXCEPTION = new ExceptionCode("ACCS5001", "ACCS_UNKNOWN_EXCEPTION", "An unexcepted exception occured",HttpStatus.INTERNAL_SERVER_ERROR);
	public static final ExceptionCode ACC_CUSTOMER_NON_EXIST = new ExceptionCode("ACCS5002", "ACC_CUSTOMER_NON_EXIST", "Customer does not exists",HttpStatus.INTERNAL_SERVER_ERROR);
	public static final ExceptionCode ACCS_DB_EXCEPTION = new ExceptionCode("ACCS5002", "ACCS_DB_EXCEPTION", "Database level exception ocurred",HttpStatus.INTERNAL_SERVER_ERROR);
	
	
	
	//Client Input Error Series 
	public static final ExceptionCode ACCS_INVALID_INPUT = new ExceptionCode("ACCS4001", "ACCS_INVALID_INPUT", "Missing or invalid request parameters",HttpStatus.BAD_REQUEST);
	public static final ExceptionCode ACCS_CUSTOMER_ALREADY_EXISTS = new ExceptionCode("ACCS4003", "ACCS_CUSTOMER_ALREADY_EXISTS", "Customer already exists",HttpStatus.BAD_REQUEST);
	public static final ExceptionCode ACCS_BAD_CREDENTIALS = new ExceptionCode("ACCS4004", "ACCS_BAD_CREDENTIALS", "Bad Credentials",HttpStatus.UNAUTHORIZED);
	public static final ExceptionCode ACCS_JWT_ERROR = new ExceptionCode("ACCS4003", "ACCS_INVALID_JWT", "Authentication failed.{0}",HttpStatus.UNAUTHORIZED);
	public static final ExceptionCode ACC_AUTHZ_ERROR = new ExceptionCode("ACCS4005", "ACC_AUTHZ_ERROR", "Autheorization error",HttpStatus.FORBIDDEN);

}
