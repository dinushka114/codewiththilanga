package com.CodeWithThilanga.GoCheetaOnline.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	private static Connection connection;
	
	private DBConnection() {}
	
	
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException{
		
		if (connection == null || connection.isClosed()) {
			String url = "jdbc:mysql://localhost:3306/gocheetaonline";
			String username = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url , username , password);
		}
		return connection;
	}
	
}
