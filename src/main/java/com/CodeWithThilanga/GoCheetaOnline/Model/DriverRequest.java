package com.CodeWithThilanga.GoCheetaOnline.Model;

public class DriverRequest {
	private String requestId;
	private String customerId;
	private String vehicleId;
	private String source;
	private String destination;
	private String status;
	private String Date;
	
	
	
	
	
	public DriverRequest(String requestId, String customerId, String vehicleId, String source, String destination,
			String status, String date) {
		super();
		this.requestId = requestId;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.source = source;
		this.destination = destination;
		this.status = status;
		Date = date;
	}
	public DriverRequest(String customerId, String vehicleId, String source, String destination, String status,
			String date) {
		super();
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.source = source;
		this.destination = destination;
		this.status = status;
		Date = date;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
	
}
