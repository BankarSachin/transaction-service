package com.smartbank.transactionservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Holds Account Information
 * <p>
 * For simplicity we are having one to to one mapping between {@link Account} and {@link Customer}
 * @author Sachin
 */
@Entity
@Data
@Table(name = "accounts")
public class Account {

	@Id
	@Column(name="account_number",nullable = false,length = 10)	
	private String accountNumber;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Customer.class)
	@JoinColumn(name="customer_id",
	            nullable = false,
	            referencedColumnName = "customer_id",
	            updatable = false)
	private Customer customer;
}
