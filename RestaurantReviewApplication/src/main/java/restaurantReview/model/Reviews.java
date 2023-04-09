package restaurantReview.model;

import java.util.Date;

public class Reviews {

	protected int reviewId;
	protected double rating;
	protected Date created;
	protected String content;
	protected Users user;
	protected Restaurants restaurant;

	public Reviews(int reviewId, double rating, Date created, String content, Users user, Restaurants restaurant) {
		this.reviewId = reviewId;
		this.user = user;
		this.rating = rating;
		this.created = created;
		this.content = content;
		this.restaurant = restaurant;
	}

	public Reviews(double rating, Date created, String content, Users user, Restaurants restaurant) {
		this.rating = rating;
		this.user = user;
		this.created = created;
		this.content = content;
		this.restaurant = restaurant;
	}

	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

}