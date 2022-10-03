package com.CodeWithThilanga.GoCheetaOnline.Dao;

public class DriverServiceFactory {
	public static IDriverService getInstance() {
		return new DriverServiceImpl();
	}
}
