package com.Bean;

import java.sql.Timestamp;

public class AccountStatus extends Account {
	private String status;
	private String message;
	private Timestamp lastUpdate;
	public AccountStatus(int custId,int acctId,String acctType,String status, String message, Timestamp lastUpdate) {
		super(custId,acctId,acctType);
		this.status = status;
		this.message = message;
		this.lastUpdate = lastUpdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
