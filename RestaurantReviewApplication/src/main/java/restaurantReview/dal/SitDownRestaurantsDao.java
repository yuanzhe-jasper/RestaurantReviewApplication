package restaurantReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurantReview.model.Companies;
import restaurantReview.model.Restaurants;
import restaurantReview.model.SitDownRestaurant;

//public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant)
//public SitDownRestaurants getSitDownRestaurantById(int sitDownRestaurantId)
//public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName)
//public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurant)

public class SitDownRestaurantsDao extends RestaurantsDao {

	private static SitDownRestaurantsDao instance = null;

	protected SitDownRestaurantsDao() {
		super();
	}

	public static SitDownRestaurantsDao getInstance() {
		if (instance == null) {
			instance = new SitDownRestaurantsDao();
		}
		return instance;
	}

	public SitDownRestaurant create(SitDownRestaurant sitDownRestaurant) throws SQLException {

		create(new Restaurants(sitDownRestaurant.getRestaurantId(), sitDownRestaurant.getRestaurantName(),
				sitDownRestaurant.getRestaurantDescription(), sitDownRestaurant.getMenu(),
				sitDownRestaurant.getOpenHours(), sitDownRestaurant.getStreet1(), sitDownRestaurant.getStreet2(),
				sitDownRestaurant.getCity(), sitDownRestaurant.getState(), sitDownRestaurant.getZipCode(),
				sitDownRestaurant.isActive(), sitDownRestaurant.getCuisineType(), sitDownRestaurant.getCompany()));

		String insertSitDownRestaurant = "INSERT INTO SitDownRestaurants(RestaurantId, Capacity) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitDownRestaurant);
			insertStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());

			insertStmt.executeUpdate();
			return sitDownRestaurant;
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

	public SitDownRestaurant getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException {
		String selectSitDownRestaurant = "SELECT * FROM SitDownRestaurants INNER JOIN Restaurants "
				+ "  ON SitDownRestaurants.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE SitDownRestaurants.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CompaniesDao companyDao = CompaniesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
			selectStmt.setInt(1, sitDownRestaurantId);
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
				int capacity = results.getInt("Capacity");

				Companies company = companyDao.getCompanyByCompanyName(companyName);
				SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(resultRestaurantId, restaurantName,
						description, menu, hours, street1, street2, city, state, zipCode, active, cuisineType, company,
						capacity);
				return sitDownRestaurant;

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

	public List<SitDownRestaurant> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException {
		List<SitDownRestaurant> sitDownRestaurants = new ArrayList<>();

		String selectSitDownRestaurant = "SELECT *" + "FROM SitDownRestaurants INNER JOIN Restaurants "
				+ "  ON SitDownRestaurants.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CompaniesDao companyDao = CompaniesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
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
				int capacity = results.getInt("Capacity");
				Companies company = companyDao.getCompanyByCompanyName(nameOfCompany);
				SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(resultRestaurantId, restaurantName,
						description, menu, hours, street1, street2, city, state, zipCode, active, cuisineType, company,
						capacity);
				sitDownRestaurants.add(sitDownRestaurant);
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
		return sitDownRestaurants;
	}

	public SitDownRestaurant delete(SitDownRestaurant sitDownRestaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM SitDownRestaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());
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