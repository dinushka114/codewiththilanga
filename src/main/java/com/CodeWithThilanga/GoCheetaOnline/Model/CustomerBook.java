package com.CodeWithThilanga.GoCheetaOnline.Model;

public class CustomerBook {
	private String bookingId;
	private String branchId;
	private String customerId;
	private String driverId;
	private String vehicleId;
	private float cost;
	private String source;
	private String destination;
	
	
	public CustomerBook(String branchId , String customerId, String driverId, String vehicleId, float cost , String source , String destination) {
		this.customerId = customerId;
		this.branchId = branchId;
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.cost = cost;
		this.source = source;
		this.destination = destination;
	}
	
	public CustomerBook(String bookingId,String branchId ,  String customerId, String driverId, String vehicleId, float cost ,String source , String destination) {
		this.bookingId = bookingId;
		this.branchId = branchId;
		this.customerId = customerId;
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.cost = cost;
		this.source = source;
		this.destination = destination;
	}
	
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	
}
