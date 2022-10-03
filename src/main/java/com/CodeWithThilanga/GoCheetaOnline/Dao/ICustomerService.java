package com.CodeWithThilanga.GoCheetaOnline.Dao;

import java.util.ArrayList;

import com.CodeWithThilanga.GoCheetaOnline.Model.Customer;
import com.CodeWithThilanga.GoCheetaOnline.Model.CustomerBook;

public interface ICustomerService {
	
	//add customer for customer table
	public boolean addCustomer(Customer customer);
	
	//update profile
	public boolean updateProfile(Customer customer);
	
	//get a particular customer
	public Customer getCustomer(String customerID);
	
	//get all customers	
	public ArrayList<Customer> getCustomers();
	
	//delete a particular customer
	public void removeCustomer(String customerID);

	//login a customer
	public boolean loginCustomer(String username , String password);

	//book a vehicle
	public boolean bookVehicle(CustomerBook customerBook);
	
	//get customer id
	public int getCurrentCustomerId(String username);

	//get customer branch
	public int getCurrentCustomerBranchId(String customerId);
	
	//add feedback
	public boolean addFeedback(String feedback , String booking_id , String customer_id);
}

