package restaurantReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurantReview.model.Companies;
import restaurantReview.model.FoodCartRestaurant;
import restaurantReview.model.Restaurants;

//public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant)
//public FoodCartRestaurants getFoodCartRestaurantById(int foodCartRestaurantId)
//public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName)
//public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant)
public class FoodCartRestaurantsDao extends RestaurantsDao {
	private static FoodCartRestaurantsDao instance = null;

	protected FoodCartRestaurantsDao() {
		super();
	}

	public static FoodCartRestaurantsDao getInstance() {
		if (instance == null) {
			instance = new FoodCartRestaurantsDao();
		}
		return instance;
	}

	public FoodCartRestaurant create(FoodCartRestaurant foodCartRestaurant) throws SQLException {

		create(new Restaurants(foodCartRestaurant.getRestaurantId(), foodCartRestaurant.getRestaurantName(),
				foodCartRestaurant.getRestaurantDescription(), foodCartRestaurant.getMenu(),
				foodCartRestaurant.getOpenHours(), foodCartRestaurant.getStreet1(), foodCartRestaurant.getStreet2(),
				foodCartRestaurant.getCity(), foodCartRestaurant.getState(), foodCartRestaurant.getZipCode(),
				foodCartRestaurant.isActive(), foodCartRestaurant.getCuisineType(), foodCartRestaurant.getCompany()));

		String insertFoodCartRestaurant = "INSERT INTO FoodCartRestaurants(RestaurantId, Licensed) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurant);
			insertStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());

			insertStmt.executeUpdate();
			return foodCartRestaurant;
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

	public FoodCartRestaurant getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException {
		String selectFoodCartRestaurant = "SELECT * FROM FoodCartRestaurants INNER JOIN Restaurants "
				+ "  ON FoodCartRestaurants.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE FoodCartRestaurants.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CompaniesDao companyDao = CompaniesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurant);
			selectStmt.setInt(1, foodCartRestaurantId);
			results = selectStmt.executeQuery();
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
				boolean isLicensed = results.getBoolean("Licensed");

				Companies company = companyDao.getCompanyByCompanyName(companyName);
				FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(resultRestaurantId, restaurantName,
						description, menu, hours, street1, street2, city, state, zipCode, active, cuisineType, company,
						isLicensed);
				return foodCartRestaurant;
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

	public List<FoodCartRestaurant> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException {
		List<FoodCartRestaurant> foodCartRestaurants = new ArrayList<>();
		String selectFoodCartRestaurant = "SELECT *" + "FROM FoodCartRestaurants INNER JOIN Restaurants "
				+ "  ON FoodCartRestaurants.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CompaniesDao companyDao = CompaniesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurant);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
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
				boolean isLicensed = results.getBoolean("Licensed");

				Companies company = companyDao.getCompanyByCompanyName(nameOfCompany);
				FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(resultRestaurantId, restaurantName,
						description, menu, hours, street1, street2, city, state, zipCode, active, cuisineType, company,
						isLicensed);
				foodCartRestaurants.add(foodCartRestaurant);
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
		return foodCartRestaurants;
	}

	public FoodCartRestaurant delete(FoodCartRestaurant foodCartRestaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM FoodCartRestaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());
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