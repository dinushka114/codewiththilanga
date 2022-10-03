package com.CodeWithThilanga.GoCheetaOnline.Model;

public class Customer {
	private String customerID;
	private String customerName;
	private String customerEmail;
	private String customerUsername;
	private String customerPassword;
	private String customerBranch;
	
	public Customer() {}
	
	
	public Customer( String customerName, String customerEmail, String customerUsername,
			String customerPassword, String customerBranch) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.customerBranch = customerBranch;
	}
	
	public Customer( String id , String customerName, String customerEmail, String customerUsername,
			String customerPassword, String customerBranch) {
		this.customerID = id;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.customerBranch = customerBranch;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerBranch() {
		return customerBranch;
	}
	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}
	
	
	
	
}
