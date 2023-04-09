package restaurantReview.model;

import java.util.Date;

public class Reservations {

	protected int reservationId;
	protected int size;
	protected Date startDate;
	protected Date endDate;
	protected Users user;
	protected SitDownRestaurant sitDownRestaurant;

	public Reservations(int reservationId, int size, Date startDate, Date endDate, Users user,
			SitDownRestaurant sitDownRestaurant) {
		this.reservationId = reservationId;
		this.size = size;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.sitDownRestaurant = sitDownRestaurant;
	}

	public Reservations(int size, Date startDate, Date endDate, Users user, SitDownRestaurant sitDownRestaurant) {
		this.size = size;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.sitDownRestaurant = sitDownRestaurant;
	}

	public Reservations(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public SitDownRestaurant getSitDownRestaurant() {
		return sitDownRestaurant;
	}

	public void setSitDownRestaurant(SitDownRestaurant sitDownRestaurant) {
		this.sitDownRestaurant = sitDownRestaurant;
	}

}