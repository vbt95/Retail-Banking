package com.Bean;

public class Employee {
	private String user_id;
	private String password;
	private String emp_type;
	public Employee(String user_id, String password, String emp_type) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.emp_type = emp_type;
	}
	
	public Employee(String user_id, String password) {
		super();
		this.user_id = user_id;
		this.password = password;
	}


	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmp_type() {
		return emp_type;
	}
	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}
	
	
}
