package com.CodeWithThilanga.GoCheetaOnline.Dao;

import java.util.List;

import com.CodeWithThilanga.GoCheetaOnline.Model.CustomerBook;
import com.CodeWithThilanga.GoCheetaOnline.Model.Driver;
import com.CodeWithThilanga.GoCheetaOnline.Model.Vehicle;
import com.CodeWithThilanga.GoCheetaOnline.Model.VehicleCategory;

public interface IAdminService {
	
	//login admin
	public boolean login(String username , String password);
	
	//add new vehicle category
	public boolean addVehicleCategory(VehicleCategory category);
	
	//get all vehicle categories
	public List<VehicleCategory> getAllVehicleCategories();
	
	//delete vehicle Category
	public boolean deleteVehicleCategory(String id);
	
	//update vehicle category
	public boolean updateVehicleCategory(VehicleCategory vehicleCategory);
	
	//add new driver
	public boolean addNewDriver(Driver driver);
	
	//edit a driver
	public boolean editDriver(Driver driver);
	
	//delete a driver
	public boolean deleteDriver(String id);
	
	//add new vehicle
	public boolean addNewVehicle(Vehicle vehicle);
	
	//update a vehicle
	public boolean updateVehicle(Vehicle vehicle);
	
	//delete a vehicle
	public boolean deleteVehicle(String id);
	
}
