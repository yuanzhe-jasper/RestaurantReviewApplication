package restaurantReview.model;

public class FoodCartRestaurant extends Restaurants {

	protected boolean licensed;

	public FoodCartRestaurant(int restaurantId, String restaurantName, String restaurantDescription, String menu,
			String openHours, String street1, String street2, String city, String state, String zipCode,
			boolean isActive, CuisineType cuisineType, Companies company, boolean licensed) {
		super(restaurantId, restaurantName, restaurantDescription, menu, openHours, street1, street2, city, state,
				zipCode, isActive, cuisineType, company);
		this.licensed = licensed;
	}

	public FoodCartRestaurant(String restaurantName, String restaurantDescription, String menu, String openHours,
			String street1, String street2, String city, String state, String zipCode, boolean isActive,
			CuisineType cuisineType, Companies company, boolean licensed) {
		super(restaurantName, restaurantDescription, menu, openHours, street1, street2, city, state, zipCode, isActive,
				cuisineType, company);
		this.licensed = licensed;
	}

	public FoodCartRestaurant(int restaurantid) {
		super(restaurantid);
	}

	public boolean isLicensed() {
		return licensed;
	}

	public void setLicensed(boolean licensed) {
		this.licensed = licensed;
	}

}