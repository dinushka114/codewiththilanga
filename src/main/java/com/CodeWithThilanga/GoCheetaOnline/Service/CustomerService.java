package com.CodeWithThilanga.GoCheetaOnline.Service;

import com.CodeWithThilanga.GoCheetaOnline.Dao.CustomerServiceFactory;
import com.CodeWithThilanga.GoCheetaOnline.Dao.ICustomerService;
import com.CodeWithThilanga.GoCheetaOnline.Model.Customer;
import com.CodeWithThilanga.GoCheetaOnline.Model.CustomerBook;

public class CustomerService {
	private static CustomerService customerService;
	
	private CustomerService(){}
	
	public static synchronized CustomerService getCustomerServiceInstance() {
		if(customerService ==null) {
			customerService = new CustomerService();
		}
		
		return customerService;
	}
	
	public boolean addCustomer(Customer customer) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.addCustomer(customer);
	}
	
	public boolean loginCustomer(String username , String password) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.loginCustomer(username, password);
	}
	
	public boolean updateProfile(Customer customer) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.updateProfile(customer);
	}
	
	public boolean bookVehicle(CustomerBook customerBook) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.bookVehicle(customerBook);
	}
	
	public int getCurrentCustomerId(String username) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.getCurrentCustomerId(username);
	}
	
	public int getCurrentCustomerBranchId(String customerId) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.getCurrentCustomerBranchId(customerId);
	}
	
	public boolean addFeedback(String feedback , String booking_id , String customer_id) {
		ICustomerService iCustomerService = CustomerServiceFactory.getInstance();
		return iCustomerService.addFeedback(feedback, booking_id , customer_id);
	}
	
}
