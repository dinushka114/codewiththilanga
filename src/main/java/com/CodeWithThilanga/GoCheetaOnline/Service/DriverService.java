package com.CodeWithThilanga.GoCheetaOnline.Service;

import com.CodeWithThilanga.GoCheetaOnline.Dao.DriverServiceFactory;
import com.CodeWithThilanga.GoCheetaOnline.Dao.IDriverService;

public class DriverService {
private static DriverService driverService;
	
	private DriverService(){}
	
	public static synchronized DriverService getDriverServiceInstance() {
		if(driverService ==null) {
			driverService = new DriverService();
		}
		
		return driverService;
	}
	
	public boolean driverLogin(String email , String phone) {
		IDriverService iDriverService = DriverServiceFactory.getInstance();
		return iDriverService.driverLogin(email, phone);
	}
	
	public boolean processBooking(String vehicle_id ,String request_id ) {
		IDriverService iDriverService = DriverServiceFactory.getInstance();
		return iDriverService.processBook(vehicle_id , request_id);
	}
	
	public boolean completeBooking(String vehicle_id ,String request_id) {
		IDriverService iDriverService = DriverServiceFactory.getInstance();
		return iDriverService.completeBook(vehicle_id , request_id);
	}
	
	public boolean cancleBooking(String vehicle_id , String request_id) {
		IDriverService iDriverService = DriverServiceFactory.getInstance();
		return iDriverService.canelBook(vehicle_id , request_id);
	}
}
