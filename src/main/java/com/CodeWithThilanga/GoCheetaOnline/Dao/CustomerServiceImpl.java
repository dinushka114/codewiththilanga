package com.CodeWithThilanga.GoCheetaOnline.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.CodeWithThilanga.GoCheetaOnline.Model.Customer;
import com.CodeWithThilanga.GoCheetaOnline.Model.CustomerBook;


public class CustomerServiceImpl implements ICustomerService{
	private static Connection connection;
	private static Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	@Override
	public boolean addCustomer(Customer customer) {
		boolean res = false;
		try {
				
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO customer (customer_name , customer_email , customer_username , customer_password , customer_branch) values(? , ? , ? , ? , ?)");
			preparedStatement.setString(1,customer.getCustomerName());
			preparedStatement.setString(2,customer.getCustomerEmail());
			preparedStatement.setString(3,customer.getCustomerUsername());
			preparedStatement.setString(4,customer.getCustomerPassword());
			preparedStatement.setString(5,customer.getCustomerBranch());
			
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
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
	public Customer getCustomer(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void removeCustomer(String customerID) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean loginCustomer(String username, String password) {
		boolean result = false;
		try {
			String usernamedb , passworddb;
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("select customer_username , customer_password from customer");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				usernamedb = resultSet.getString("customer_username");
				passworddb = resultSet.getString("customer_password");
				
				if(usernamedb.equals(username) && passworddb.equals(password)) {
					result = true;
				}
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			result = false;
			
		}finally {
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
	public boolean updateProfile(Customer customer) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE customer set customer_name = ? , customer_email = ?  , customer_branch = ? where customer_id = ? ");
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerEmail());
			preparedStatement.setString(3, customer.getCustomerBranch());
			preparedStatement.setInt(4, Integer.parseInt(customer.getCustomerID()));

			result = preparedStatement.executeUpdate() > 0;



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
		
		return result;
	}


	@Override
	public boolean bookVehicle(CustomerBook customerBook) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO customer_booking (branch_id , customer_id , driver_id , vehicle_id , source , destination , cost) values(? ,? , ? , ? , ? , ? , ?)");
			preparedStatement.setString(1, customerBook.getBranchId());
			preparedStatement.setString(2 , customerBook.getCustomerId());
			preparedStatement.setString(3, customerBook.getDriverId());
			preparedStatement.setString(4, customerBook.getVehicleId());
			preparedStatement.setString(5, customerBook.getSource());
			preparedStatement.setString(6, customerBook.getDestination());
			preparedStatement.setFloat(7, customerBook.getCost());
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			preparedStatement = connection.prepareStatement("INSERT INTO driver_request (driver_id , customer_id , vehicle_id , source , destination) values(? , ? , ? , ? ,?) ");
			preparedStatement.setString(1, customerBook.getDriverId());
			preparedStatement.setString(2, customerBook.getCustomerId());
			preparedStatement.setString(3 , customerBook.getVehicleId());
			preparedStatement.setString(4, customerBook.getSource());
			preparedStatement.setString(5, customerBook.getDestination());
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			
//			preparedStatement = connection.prepareStatement("UPDATE vehicle set is_available = ?  where vehicle_id = ?");
//			preparedStatement.setBoolean(1, false);
//			preparedStatement.setInt(2, Integer.parseInt(customerBook.getVehicleId()));
//			preparedStatement.execute();
//			connection.commit();
//			res = true;

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
	public int getCurrentCustomerId(String username) {
		int customerID =  0 ;
		try {
		
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("select customer_id  from customer where customer_username = ?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				customerID = resultSet.getInt("customer_id");
				
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
		}finally {
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
		
		return customerID;
	}


	@Override
	public int getCurrentCustomerBranchId(String customerId) {
		int branchId =  0 ;
		try {
		
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("select customer_branch from customer where customer_id = ? ");
			preparedStatement.setInt(1, Integer.parseInt(customerId));
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				branchId = resultSet.getInt("customer_branch");
				
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
		}finally {
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
		
		return branchId;
	}
	
	@Override
	public boolean addFeedback(String feedback ,String booking_id , String customer_id) {
		boolean res = false;
		try {
				
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO feedback (feedback , booking_id , customer_id) values(? , ? , ?)");
			preparedStatement.setString(1,feedback);
			preparedStatement.setString(2,booking_id);
			preparedStatement.setString(3,customer_id);
			preparedStatement.execute();
			connection.commit();
			res = true;
			
			
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
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
