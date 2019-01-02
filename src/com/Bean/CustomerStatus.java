package com.Bean;
import java.sql.Timestamp;
public class CustomerStatus extends Customer{
private String status;
private String message;
private Timestamp lastUpdate;
public CustomerStatus(int SSN,int customerID, String status, String message, Timestamp lastUpdate) {
super.setSSN(SSN);
super.setCustomerID(customerID);
this.status = status;
this.message = message;
this.lastUpdate = lastUpdate;
}
public CustomerStatus(int customerID,int SSN, String customerName, int age,
        String addressLine1, String addressLine2, String city,
        String state, String status, String message) {
    super(customerID,SSN, customerName, age, addressLine1, addressLine2, city, state);
    this.status = status;
    this.message = message;
}


public CustomerStatus() {
    super();
    // TODO Auto-generated constructor stub
}

public CustomerStatus(int customerID, int SSN, String customerName,
        int age, String addressLine1, String addressLine2, String city,
        String state) {
    super(customerID, SSN, customerName, age, addressLine1, addressLine2, city,
            state);
    // TODO Auto-generated constructor stub
}

public CustomerStatus(int SSN, String customerName, int age,
        String addressLine1, String addressLine2, String city, String state) {
    super(SSN, customerName, age, addressLine1, addressLine2, city, state);
    // TODO Auto-generated constructor stub
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

