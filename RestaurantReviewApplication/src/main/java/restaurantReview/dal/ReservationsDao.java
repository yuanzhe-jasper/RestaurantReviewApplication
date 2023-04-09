package restaurantReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//public Reservations create(Reservations reservation)

//public Reservations getReservationById(int reservationId)
//public List<Reservations> getReservationsByUserName(String userName)
//public List<Reservations> getReservationsBySitDownRestaurantId(int sitDownRestaurantId)
//public Reservations delete(Reservations reservation)

import restaurantReview.model.Reservations;
import restaurantReview.model.SitDownRestaurant;
import restaurantReview.model.Users;

public class ReservationsDao {

	protected ConnectionManager connectionManager;

	private static ReservationsDao instance = null;

	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}

	public static ReservationsDao getInstance() {
		if (instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}

	public Reservations create(Reservations reservation) throws SQLException {

		String insertReservation = "INSERT INTO Reservations(Start, End, Size, UserName, RestaurantId) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservation, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setTimestamp(1, new Timestamp(reservation.getStartDate().getTime()));
			insertStmt.setTimestamp(2, new Timestamp(reservation.getEndDate().getTime()));
			insertStmt.setInt(3, reservation.getSize());
			insertStmt.setString(4, reservation.getUser().getUserName());
			insertStmt.setInt(5, reservation.getSitDownRestaurant().getRestaurantId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reservationId = -1;
			if (resultKey.next()) {
				reservationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reservation.setReservationId(reservationId);
			return reservation;
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

	public Reservations getReservationById(int reservationId) throws SQLException {
		String selectReservation = "SELECT * FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservation);
			selectStmt.setInt(1, reservationId);
			results = selectStmt.executeQuery();

			UsersDao userDao = UsersDao.getInstance();
			SitDownRestaurantsDao sitDownRestaurantDao = SitDownRestaurantsDao.getInstance();
			if (results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				Date startTime = new Date(results.getTimestamp("Start").getTime());
				Date endTime = new Date(results.getTimestamp("End").getTime());
				String userName = results.getString("UserName");
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");

				Users user = userDao.getUserByUserName(userName);
				SitDownRestaurant restaurant = sitDownRestaurantDao.getSitDownRestaurantById(restaurantId);

				Reservations reservation = new Reservations(resultReservationId, size, startTime, endTime, user,
						restaurant);
				return reservation;
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

	public List<Reservations> getReservationsByUserName(String userName) throws SQLException {

		List<Reservations> reservations = new ArrayList<>();
		String selectReservations = "SELECT *  FROM Reservations WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();

			UsersDao userDao = UsersDao.getInstance();
			SitDownRestaurantsDao sitDownRestaurantDao = SitDownRestaurantsDao.getInstance();

			while (results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				// Date created = new Date(results.getTimestamp("Created").getTime());
				Date startTime = new Date(results.getTimestamp("Start").getTime());
				Date endTime = new Date(results.getTimestamp("End").getTime());
				String nameOfUser = results.getString("UserName");
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");

				Users user = userDao.getUserByUserName(nameOfUser);
				SitDownRestaurant restaurant = sitDownRestaurantDao.getSitDownRestaurantById(restaurantId);

				Reservations reservation = new Reservations(resultReservationId, size, startTime, endTime, user,
						restaurant);
				reservations.add(reservation);
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
		return reservations;
	}

	public List<Reservations> getReservationsBySitDownRestaurantId(int sitDownRestaurantId) throws SQLException {
		List<Reservations> reservations = new ArrayList<>();
		String selectReservations = "SELECT *  FROM Reservations WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1, sitDownRestaurantId);
			results = selectStmt.executeQuery();

			UsersDao userDao = UsersDao.getInstance();
			SitDownRestaurantsDao sitDownRestaurantDao = SitDownRestaurantsDao.getInstance();

			while (results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				// Date created = new Date(results.getTimestamp("Created").getTime());
				Date startTime = new Date(results.getTimestamp("Start").getTime());
				Date endTime = new Date(results.getTimestamp("End").getTime());
				String nameOfUser = results.getString("UserName");
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");

				Users user = userDao.getUserByUserName(nameOfUser);
				SitDownRestaurant restaurant = sitDownRestaurantDao.getSitDownRestaurantById(restaurantId);

				Reservations reservation = new Reservations(resultReservationId, size, startTime, endTime, user,
						restaurant);
				reservations.add(reservation);
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
		return reservations;
	}

	public Reservations delete(Reservations reservation) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, reservation.getReservationId());
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