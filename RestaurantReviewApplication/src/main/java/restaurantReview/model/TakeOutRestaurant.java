package restaurantReview.model;

public class TakeOutRestaurant extends Restaurants {
	protected int maxWaitTime;

	public TakeOutRestaurant(int restaurantId, String restaurantName, String restaurantDescription, String menu,
			String openHours, String street1, String street2, String city, String state, String zipCode,
			boolean isActive, CuisineType cuisineType, Companies company, int maxWaitTime) {
		super(restaurantId, restaurantName, restaurantDescription, menu, openHours, street1, street2, city, state,
				zipCode, isActive, cuisineType, company);
		// TODO Auto-generated constructor stub
		this.maxWaitTime = maxWaitTime;
	}

	public TakeOutRestaurant(String restaurantName, String restaurantDescription, String menu, String openHours,
			String street1, String street2, String city, String state, String zipCode, boolean isActive,
			CuisineType cuisineType, Companies company, int maxWaitTime) {
		super(restaurantName, restaurantDescription, menu, openHours, street1, street2, city, state, zipCode, isActive,
				cuisineType, company);
		// TODO Auto-generated constructor stub
		this.maxWaitTime = maxWaitTime;
	}

	public TakeOutRestaurant(int restaurantId) {
		super(restaurantId);
		// TODO Auto-generated constructor stub
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

}