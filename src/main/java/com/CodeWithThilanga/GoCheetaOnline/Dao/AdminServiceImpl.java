package com.CodeWithThilanga.GoCheetaOnline.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.CodeWithThilanga.GoCheetaOnline.Model.CustomerBook;
import com.CodeWithThilanga.GoCheetaOnline.Model.Driver;
import com.CodeWithThilanga.GoCheetaOnline.Model.Vehicle;
import com.CodeWithThilanga.GoCheetaOnline.Model.VehicleCategory;

public class AdminServiceImpl implements IAdminService {

	private static Connection connection;
	private static Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public boolean login(String username, String password) {
		boolean result = false;
		try {
			String usernamedb, passworddb;
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("select username , password from admin");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				usernamedb = resultSet.getString("username");
				passworddb = resultSet.getString("password");

				if (usernamedb.equals(username) && passworddb.equals(password)) {
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
	public boolean addVehicleCategory(VehicleCategory category) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO vehicle_category (category , price ) values(? , ?)");
			preparedStatement.setString(1, category.getCategory());
			preparedStatement.setFloat(2, category.getCategoryPrice());
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
	public List<VehicleCategory> getAllVehicleCategories() {
		List<VehicleCategory> categories = new ArrayList<>();

		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("SELECT * FROM vehicle_category");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("category_id");
				String category = resultSet.getString("category");
				float price = resultSet.getFloat("price");

				VehicleCategory vehicleCategory = new VehicleCategory(id, price , category);
				categories.add(vehicleCategory);
			}

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

		return categories;
	}

	@Override
	public boolean deleteVehicleCategory(String id) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("delete from vehicle_category where category_id = ? ");
			preparedStatement.setInt(1 , Integer.parseInt(id));
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
	public boolean updateVehicleCategory(VehicleCategory vehicleCategory) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE vehicle_category set category = ? , price = ?  where category_id = ?");
			preparedStatement.setString(1, vehicleCategory.getCategory());
			preparedStatement.setFloat(2, vehicleCategory.getCategoryPrice());
			preparedStatement.setInt(3, Integer.parseInt(vehicleCategory.getCategoryId()));
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
	public boolean addNewDriver(Driver driver) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO driver (driver_name , driver_contact ,driver_email,driver_branch) values(?,?,?,?)");
			preparedStatement.setString(1, driver.getDriverName());
			preparedStatement.setString(2, driver.getDriverContact());
			preparedStatement.setString(3, driver.getDriverEmail());
			preparedStatement.setInt(4, Integer.parseInt(driver.getDriverBranch()));
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
	public boolean editDriver(Driver driver) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE driver set driver_name = ? , driver_contact = ? , driver_email = ? , driver_branch = ? where driver_id = ?");
			preparedStatement.setString(1, driver.getDriverName());
			preparedStatement.setString(2, driver.getDriverContact());
			preparedStatement.setString(3, driver.getDriverEmail());
			preparedStatement.setString(4, driver.getDriverBranch());
			preparedStatement.setInt(5, Integer.parseInt(driver.getDriverId()));
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
	public boolean deleteDriver(String id) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("delete from driver where driver_id = ? ");
			preparedStatement.setInt(1 , Integer.parseInt(id));
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
	public boolean addNewVehicle(Vehicle vehicle) {
		boolean res = false;
		try {

			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO vehicle (vehicle_name , vehicle_no , vehicle_category , vehicle_branch , vehicle_driver) values(? , ? , ? , ? , ?)");
			preparedStatement.setString(1, vehicle.getVehicle_name());
			preparedStatement.setString(2, vehicle.getVehicle_number());
			preparedStatement.setInt(3, Integer.parseInt(vehicle.getVehicle_category()));
			preparedStatement.setInt(4, Integer.parseInt(vehicle.getVehicle_branch()));
			preparedStatement.setInt(5, Integer.parseInt(vehicle.getVehicle_driver()));
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
	public boolean updateVehicle(Vehicle vehicle) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("UPDATE vehicle set vehicle_name = ? , vehicle_no = ? , vehicle_category = ? , vehicle_branch = ? , vehicle_driver = ? where vehicle_id = ? ");
			preparedStatement.setString(1, vehicle.getVehicle_name());
			preparedStatement.setString(2, vehicle.getVehicle_number());
			preparedStatement.setInt(3, Integer.parseInt(vehicle.getVehicle_category()));
			preparedStatement.setInt(4 , Integer.parseInt(vehicle.getVehicle_branch()));
			preparedStatement.setInt(5, Integer.parseInt(vehicle.getVehicle_driver()));
			preparedStatement.setInt(6, Integer.parseInt(vehicle.getVehicle_id()));
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
	public boolean deleteVehicle(String id) {
		boolean result = false;
		try {

			connection = DBConnection.getDBConnection();
			preparedStatement = connection.prepareStatement("delete from vehicle where vehicle_id = ? ");
			preparedStatement.setInt(1 , Integer.parseInt(id));
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

	

}
