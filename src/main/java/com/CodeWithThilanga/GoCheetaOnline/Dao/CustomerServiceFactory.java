package com.CodeWithThilanga.GoCheetaOnline.Dao;

public class CustomerServiceFactory {
		public static ICustomerService getInstance() {
			return new CustomerServiceImpl();
		}
}
