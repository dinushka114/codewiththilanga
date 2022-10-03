package com.CodeWithThilanga.GoCheetaOnline.Service;

import java.util.List;

import com.CodeWithThilanga.GoCheetaOnline.Dao.AdminServiceFactory;
import com.CodeWithThilanga.GoCheetaOnline.Dao.IAdminService;
import com.CodeWithThilanga.GoCheetaOnline.Model.Driver;
import com.CodeWithThilanga.GoCheetaOnline.Model.Vehicle;
import com.CodeWithThilanga.GoCheetaOnline.Model.VehicleCategory;

public class AdminService {
private static AdminService adminService;
	
	private AdminService(){}
	
	public static synchronized AdminService getAdminServiceInstance() {
		if(adminService ==null) {
			adminService = new AdminService();
		}
		
		return adminService;
	}
	
	public boolean loginAdmin(String username , String password) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.login(username, password);
	}
	
	public boolean addVehicleCategory(VehicleCategory category) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.addVehicleCategory(category);
	}
	
	public List<VehicleCategory> getAllVehicleCategories(){
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.getAllVehicleCategories();
	}
	
	public boolean deleteVehicleCategory(String id) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.deleteVehicleCategory(id);
	}
	
	public boolean updateVehicleCategory(VehicleCategory vehicleCategory) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.updateVehicleCategory(vehicleCategory);
	}
	
	public boolean addNewDriver(Driver driver) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.addNewDriver(driver);
	}
	
	public boolean editDriver(Driver driver) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.editDriver(driver);
	}
	
	public boolean deleteDriver(String id) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.deleteDriver(id);
	}

	public boolean addNewVehicle(Vehicle vehicle) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.addNewVehicle(vehicle);
	}
	
	public boolean updateVehicle(Vehicle vehicle) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.updateVehicle(vehicle);
	}
	
	public boolean deleteVehicle(String id) {
		IAdminService iAdminService = AdminServiceFactory.getInstance();
		return iAdminService.deleteVehicle(id);
	}

}
