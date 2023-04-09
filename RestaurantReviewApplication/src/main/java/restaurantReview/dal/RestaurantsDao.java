package restaurantReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurantReview.model.Companies;
import restaurantReview.model.Restaurants;

public class RestaurantsDao {

	protected ConnectionManager connectionManager;
	private static RestaurantsDao instance = null;

	protected RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}

	public static RestaurantsDao getInstance() {
		if (instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}

	public Restaurants create(Restaurants restaurant) throws SQLException {

		String insertRestaurant = "INSERT INTO Restaurants(Name, Description, Menu, Hours, Active, CuisineType,Street1, Street2, City, State, Zip, CompanyName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant);

			insertStmt.setString(1, restaurant.getRestaurantName());
			insertStmt.setString(2, restaurant.getRestaurantDescription());
			insertStmt.setString(3, restaurant.getMenu());
			insertStmt.setString(4, restaurant.getOpenHours());
			insertStmt.setBoolean(5, restaurant.isActive());
			insertStmt.setString(6, restaurant.getCuisineType().name());
			insertStmt.setString(7, restaurant.getStreet1());
			insertStmt.setString(8, restaurant.getStreet2());
			insertStmt.setString(9, restaurant.getCity());
			insertStmt.setString(10, restaurant.getState());
			insertStmt.setString(11, restaurant.getZipCode());
			insertStmt.setString(12, restaurant.getCompany().getCompanyName());
			insertStmt.executeUpdate();

			return restaurant;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Restaurants getRestaurantById(int restaurantId) throws SQLException {
		String selectRestaurant = "SELECT * FROM Restaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, restaurantId);

			results = selectStmt.executeQuery();
			CompaniesDao companyDao = CompaniesDao.getInstance();
			if (results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("street1");
				String street2 = results.getString("street2");
				String city = results.getString("city");
				String state = results.getString("State");
				String zipCode = results.getString("Zip");
				String companyName = results.getString("CompanyName");
				Companies company = companyDao.getCompanyByCompanyName(companyName);
				Restaurants restaurant = new Restaurants(resultRestaurantId, restaurantName, description, menu, hours,
						street1, street2, city, state, zipCode, active, cuisineType, company);
				return restaurant;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Restaurants> getRestaurantsByCuisine(Restaurants.CuisineType cuisine) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<>();
		String selectRestaurants = "SELECT * FROM Restaurants WHERE CuisineType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, cuisine.name());
			results = selectStmt.executeQuery();
			CompaniesDao companyDao = CompaniesDao.getInstance();
			while (results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("street1");
				String street2 = results.getString("street2");
				String city = results.getString("city");
				String state = results.getString("State");
				String zipCode = results.getString("Zip");
				String companyName = results.getString("CompanyName");
				Companies company = companyDao.getCompanyByCompanyName(companyName);
				Restaurants restaurant = new Restaurants(resultRestaurantId, restaurantName, description, menu, hours,
						street1, street2, city, state, zipCode, active, cuisineType, company);
				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return restaurants;
	}

	public List<Restaurants> getRestaurantsByCompanyName(String companyName) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<>();
		String selectRestaurants = "SELECT * FROM Restaurants WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			CompaniesDao companyDao = CompaniesDao.getInstance();
			while (results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("street1");
				String street2 = results.getString("street2");
				String city = results.getString("city");
				String state = results.getString("State");
				String zipCode = results.getString("Zip");
				String nameOfCompany = results.getString("CompanyName");
				Companies company = companyDao.getCompanyByCompanyName(nameOfCompany);
				Restaurants restaurant = new Restaurants(resultRestaurantId, restaurantName, description, menu, hours,
						street1, street2, city, state, zipCode, active, cuisineType, company);
				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return restaurants;

	}

	public Restaurants delete(Restaurants restaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM Restaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}

	}

}