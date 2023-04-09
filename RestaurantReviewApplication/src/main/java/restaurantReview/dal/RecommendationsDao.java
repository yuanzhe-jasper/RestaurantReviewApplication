
package restaurantReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restaurantReview.model.Recommendations;
import restaurantReview.model.Restaurants;
import restaurantReview.model.Users;

public class RecommendationsDao {

	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;

	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}

	public static RecommendationsDao getInstance() {
		if (instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	public Recommendations create(Recommendations recommendation) throws SQLException {

		String insertRecommendation = "INSERT INTO Recommendations(UserName, RestaurantId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, recommendation.getUser().getUserName());
			insertStmt.setInt(2, recommendation.getRestaurant().getRestaurantId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if (resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);
			return recommendation;
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
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Recommendations getRecommendationById(int recommendationId) throws SQLException {
//		  RecommendationId INT AUTO_INCREMENT,
//		  UserName VARCHAR(255),
//		  RestaurantId INT,
		String selectRecommendation = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao userDao = UsersDao.getInstance();
		RestaurantsDao restaurantDao = RestaurantsDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int resultRecommendationId = results.getInt("RecommendationId");
				String nameOfUser = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");

				Users user = userDao.getUserByUserName(nameOfUser);
				Restaurants restaurant = restaurantDao.getRestaurantById(restaurantId);
				Recommendations recommendation = new Recommendations(resultRecommendationId, user, restaurant);
				return recommendation;
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

	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<>();
		String selectRecommendations = "SELECT * FROM Recommendations WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();

			UsersDao userDao = UsersDao.getInstance();
			RestaurantsDao restaurantDao = RestaurantsDao.getInstance();

			while (results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				String nameOfUser = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				Users user = userDao.getUserByUserName(nameOfUser);
				Restaurants restaurant = restaurantDao.getRestaurantById(restaurantId);
				Recommendations recommendation = new Recommendations(recommendationId, user, restaurant);
				recommendations.add(recommendation);
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
		return recommendations;
	}

	public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<>();
		String selectRecommendations = "SELECT * FROM Recommendations WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao userDao = UsersDao.getInstance();
		RestaurantsDao restaurantDao = RestaurantsDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				String nameOfUser = results.getString("UserName");
				int idOfRestaurant = results.getInt("RestaurantId");
				Users user = userDao.getUserByUserName(nameOfUser);
				Restaurants restaurant = restaurantDao.getRestaurantById(idOfRestaurant);
				Recommendations recommendation = new Recommendations(recommendationId, user, restaurant);
				recommendations.add(recommendation);
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
		return recommendations;
	}

	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
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
