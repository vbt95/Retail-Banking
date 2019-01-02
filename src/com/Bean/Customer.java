package com.Bean;

public class Customer{
	
	private int SSN;
	private int CustomerID;
	private String CustomerName;
	private int age;
	private String AddressLine1;
	private String AddressLine2;
	private String City;
	private String State;
	
	public Customer(int SSN, String customerName,
			int age, String addressLine1, String addressLine2, String city,
			String state) {
		
		this.SSN = SSN;
		CustomerName = customerName;
		this.age = age;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
		City = city;
		State = state;
	}
	public Customer(int customerID,int SSN, String customerName,
			int age, String addressLine1, String addressLine2, String city,
			String state) {
		this.CustomerID = customerID;
		this.SSN = SSN;
		CustomerName = customerName;
		this.age = age;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
		City = city;
		State = state;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
		SSN = sSN;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddressLine1() {
		return AddressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return AddressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	

}