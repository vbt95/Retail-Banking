package com.Bean;

import java.sql.Timestamp;

public class Transaction {
	private String id;
	private int accountId;
	private Timestamp transactionDate;
	private String description;
	private String type;
	private double balance;
	public Transaction(String id, int accountId, Timestamp transactionDate,
			String description, String type, double balance) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.transactionDate = transactionDate;
		this.description = description;
		this.type = type;
		this.balance = balance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
