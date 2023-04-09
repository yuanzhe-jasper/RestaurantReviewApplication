package restaurantReview.model;

public class SitDownRestaurant extends Restaurants {

	protected int capacity;

	public SitDownRestaurant(int restaurantId, String restaurantName, String restaurantDescription, String menu,
			String openHours, String street1, String street2, String city, String state, String zipCode,
			boolean isActive, CuisineType cuisineType, Companies company, int capacity) {
		super(restaurantId, restaurantName, restaurantDescription, menu, openHours, street1, street2, city, state,
				zipCode, isActive, cuisineType, company);
		this.capacity = capacity;
	}

	public SitDownRestaurant(String restaurantName, String restaurantDescription, String menu, String openHours,
			String street1, String street2, String city, String state, String zipCode, boolean isActive,
			CuisineType cuisineType, Companies company, int capacity) {
		super(restaurantName, restaurantDescription, menu, openHours, street1, street2, city, state, zipCode, isActive,
				cuisineType, company);
		this.capacity = capacity;
	}

	public SitDownRestaurant(int restaurantId) {
		super(restaurantId);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}