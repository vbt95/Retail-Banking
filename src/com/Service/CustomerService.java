package com.Service;

import java.util.ArrayList;

import com.Bean.Customer;
import com.Bean.CustomerStatus;
import com.Dao.CustomerDao;
public class CustomerService {
	public int addCustomer(Customer cust){
		CustomerDao dao=new CustomerDao();
		int result=dao.addNewCustomer(cust);
		return result;
	}
	
	public ArrayList<CustomerStatus> viewAllStatus(){
		CustomerDao dao=new CustomerDao();
		return dao.viewAllStatus();
	}
	public int deleteCustomer(int custID){
		
		CustomerDao dao = new CustomerDao();
		return dao.deleteCustomer(custID);
		
	}
}
