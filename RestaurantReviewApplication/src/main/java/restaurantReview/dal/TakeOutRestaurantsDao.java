package restaurantReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurantReview.model.Companies;
import restaurantReview.model.Restaurants;
import restaurantReview.model.TakeOutRestaurant;

public class TakeOutRestaurantsDao extends RestaurantsDao {

	private static TakeOutRestaurantsDao instance = null;

	protected TakeOutRestaurantsDao() {
		super();
	}

	public static TakeOutRestaurantsDao getInstance() {
		if (instance == null) {
			instance = new TakeOutRestaurantsDao();
		}
		return instance;
	}

	public TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant) throws SQLException {

		create(new Restaurants(takeOutRestaurant.getRestaurantId(), takeOutRestaurant.getRestaurantName(),
				takeOutRestaurant.getRestaurantDescription(), takeOutRestaurant.getMenu(),
				takeOutRestaurant.getOpenHours(), takeOutRestaurant.getStreet1(), takeOutRestaurant.getStreet2(),
				takeOutRestaurant.getCity(), takeOutRestaurant.getState(), takeOutRestaurant.getZipCode(),
				takeOutRestaurant.isActive(), takeOutRestaurant.getCuisineType(), takeOutRestaurant.getCompany()));

		String insertTakeOutRestaurant = "INSERT INTO TakeOutRestaurants(RestaurantId, MaxWaitTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOutRestaurant);
			insertStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());

			insertStmt.executeUpdate();
			return takeOutRestaurant;
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

	public TakeOutRestaurant getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException {
		// To build an TakeOutRestaurant object, we need the Restaurants record, too.
		String selectTakeOutRestaurant = "SELECT * FROM TakeOutRestaurants INNER JOIN Restaurants "
				+ "  ON TakeOutRestaurants.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE TakeOutRestaurants.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CompaniesDao companyDao = CompaniesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setInt(1, takeOutRestaurantId);
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
				int maxWaitTime = results.getInt("MaxWaitTime");

				Companies company = companyDao.getCompanyByCompanyName(companyName);
				TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(resultRestaurantId, restaurantName,
						description, menu, hours, street1, street2, city, state, zipCode, active, cuisineType, company,
						maxWaitTime);
				return takeOutRestaurant;

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

	public List<TakeOutRestaurant> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException {

		List<TakeOutRestaurant> takeOutRestaurants = new ArrayList<>();
		String selectTakeOutRestaurant = "SELECT * FROM TakeOutRestaurants INNER JOIN Restaurants "
				+ "  ON TakeOutRestaurants.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CompaniesDao companyDao = CompaniesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
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
				int maxWaitTime = results.getInt("MaxWaitTime");

				Companies company = companyDao.getCompanyByCompanyName(nameOfCompany);
				TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(resultRestaurantId, restaurantName,
						description, menu, hours, street1, street2, city, state, zipCode, active, cuisineType, company,
						maxWaitTime);
				takeOutRestaurants.add(takeOutRestaurant);

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
		return takeOutRestaurants;
	}

	public TakeOutRestaurant delete(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM TakeOutRestaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, takeOutRestaurant.getRestaurantId());
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