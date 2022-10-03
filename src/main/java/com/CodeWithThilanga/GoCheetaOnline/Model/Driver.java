package com.CodeWithThilanga.GoCheetaOnline.Model;

public class Driver {
	private String driverId;
	private String driverName;
	private String driverContact;
	private String driverEmail;
	private String driverBranch;
	
	
	
	public Driver(String driverName, String driverContact, String driverEmail, String driverBranch) {
		super();
		this.driverName = driverName;
		this.driverContact = driverContact;
		this.driverEmail = driverEmail;
		this.driverBranch = driverBranch;
	}
	public Driver(String id , String driverName, String driverContact, String driverEmail, String driverBranch) {
		this.driverId = id;
		this.driverName = driverName;
		this.driverContact = driverContact;
		this.driverEmail = driverEmail;
		this.driverBranch = driverBranch;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverContact() {
		return driverContact;
	}
	public void setDriverContact(String driverContact) {
		this.driverContact = driverContact;
	}
	public String getDriverEmail() {
		return driverEmail;
	}
	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}
	public String getDriverBranch() {
		return driverBranch;
	}
	public void setDriverBranch(String driverBranch) {
		this.driverBranch = driverBranch;
	}
}
