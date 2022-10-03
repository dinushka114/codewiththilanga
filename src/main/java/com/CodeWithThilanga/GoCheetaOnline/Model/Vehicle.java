package com.CodeWithThilanga.GoCheetaOnline.Model;

public class Vehicle {
	private String vehicle_id;
	private String vehicle_name;
	private String vehicle_number;
	private String vehicle_category;
	private String vehicle_branch;
	private String vehicle_driver;
	public Vehicle(String vehicle_id, String vehicle_name, String vehicle_number, String vehicle_category,
			String vehicle_branch, String vehicle_driver) {
		super();
		this.vehicle_id = vehicle_id;
		this.vehicle_name = vehicle_name;
		this.vehicle_number = vehicle_number;
		this.vehicle_category = vehicle_category;
		this.vehicle_branch = vehicle_branch;
		this.vehicle_driver = vehicle_driver;
	}
	public Vehicle(String vehicle_name, String vehicle_number, String vehicle_category, String vehicle_branch,
			String vehicle_driver) {
		super();
		this.vehicle_name = vehicle_name;
		this.vehicle_number = vehicle_number;
		this.vehicle_category = vehicle_category;
		this.vehicle_branch = vehicle_branch;
		this.vehicle_driver = vehicle_driver;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}
	public String getVehicle_category() {
		return vehicle_category;
	}
	public void setVehicle_category(String vehicle_category) {
		this.vehicle_category = vehicle_category;
	}
	public String getVehicle_branch() {
		return vehicle_branch;
	}
	public void setVehicle_branch(String vehicle_branch) {
		this.vehicle_branch = vehicle_branch;
	}
	public String getVehicle_driver() {
		return vehicle_driver;
	}
	public void setVehicle_driver(String vehicle_driver) {
		this.vehicle_driver = vehicle_driver;
	}
	
	
	
	
}
