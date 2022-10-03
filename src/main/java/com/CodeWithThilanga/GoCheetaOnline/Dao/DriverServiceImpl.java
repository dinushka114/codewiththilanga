package com.CodeWithThilanga.GoCheetaOnline.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverServiceImpl implements IDriverService{
	
	private static Connection connection;
	private static Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public boolean driverLogin(String email, String phone) {
		boolean result = false;
		try {
			String emailDB, phoneDB;
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("select driver_email , driver_contact from driver");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				emailDB = resultSet.getString("driver_email");
				phoneDB = resultSet.getString("driver_contact");

				if (emailDB.equals(email) && phoneDB.equals(phone)) {
					result = true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			result = false;

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public boolean processBook(String vehicle_id , String request_id) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("UPDATE vehicle set is_available = ?  where vehicle_id = ?");
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, Integer.parseInt(vehicle_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			preparedStatement = connection.prepareStatement("UPDATE customer_booking set status = ?  where vehicle_id = ?");
			preparedStatement.setString(1, "picking up");
			preparedStatement.setInt(2, Integer.parseInt(vehicle_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			preparedStatement = connection.prepareStatement("UPDATE driver_request set status = ?  where request_id = ?");
			preparedStatement.setString(1, "accept");
			preparedStatement.setInt(2, Integer.parseInt(request_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	@Override
	public boolean completeBook(String vehicle_id ,String request_id) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("UPDATE vehicle set is_available = ?  where vehicle_id = ?");
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, Integer.parseInt(vehicle_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			preparedStatement = connection.prepareStatement("UPDATE customer_booking set status = ?  where vehicle_id = ?");
			preparedStatement.setString(1, "dropped");
			preparedStatement.setInt(2, Integer.parseInt(vehicle_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			preparedStatement = connection.prepareStatement("UPDATE driver_request set status = ?  where request_id = ?");
			preparedStatement.setString(1, "completed");
			preparedStatement.setInt(2, Integer.parseInt(request_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	@Override
	public boolean canelBook(String vehicle_id ,String request_id) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);	
			preparedStatement = connection.prepareStatement("UPDATE customer_booking set status = ?  where vehicle_id = ?");
			preparedStatement.setString(1, "cancelled");
			preparedStatement.setInt(2, Integer.parseInt(vehicle_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			
			preparedStatement = connection.prepareStatement("UPDATE driver_request set status = ?  where request_id = ?");
			preparedStatement.setString(1, "declined");
			preparedStatement.setInt(2, Integer.parseInt(request_id));
			preparedStatement.execute();
			connection.commit();
			res = true;
			

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	

}
