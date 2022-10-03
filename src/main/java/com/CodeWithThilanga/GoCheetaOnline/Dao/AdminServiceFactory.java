package com.CodeWithThilanga.GoCheetaOnline.Dao;

public class AdminServiceFactory {
	public static IAdminService getInstance() {
		return new AdminServiceImpl();
	}
}	
