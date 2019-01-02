package com.Bean;

public class Account {
 
   private int accid;
   private int custid;
   private int ssnid;
   private String acctype;
   private double balance;
public Account() {
	super();
}


public Account(int accid, String acctype) {
	super();
	this.accid = accid;
	this.acctype = acctype;
}


public Account(int custid, String acctype, double balance) {
	super();
	this.custid = custid;
	this.acctype = acctype;
	this.balance= balance;
}

public Account(int accid,int custId,String acctype, double balance) {
	super();
	this.accid = accid;
	this.custid=custId;
	this.acctype = acctype;
	this.balance= balance;
}


public Account(int custid, int accid, String acctype) {
	super();
	this.custid = custid;
	this.accid = accid;
	this.acctype = acctype;
}


public int getAccid() {
	return accid;
}
public void setAccid(int accid) {
	this.accid = accid;
}
public String getAcctype() {
	return acctype;
}
public void setAcctype(String acctype) {
	this.acctype = acctype;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance= balance;
}
public int getCustid() {
	return custid;
}
public void setCustid(int custid) {
	this.custid = custid;
}

public int getSsnid() {
	return ssnid;
}

public void setSsnid(int ssnid) {
	this.ssnid = ssnid;
}
   
   
}
