package com.CodeWithThilanga.GoCheetaOnline.Dao;

public interface IDriverService {
	
	//login driver
	public boolean driverLogin(String email , String phone);
	
	//process booking
	public boolean processBook(String vehicle_id , String request_id);
	
	//complete booking
	public boolean completeBook(String vehicle_id , String request_id);
	
	//cancel booking
	public boolean canelBook(String vehicle_id ,String request_id);
	

}
