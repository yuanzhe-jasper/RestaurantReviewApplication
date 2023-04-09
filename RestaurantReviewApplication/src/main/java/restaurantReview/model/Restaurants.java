package restaurantReview.model;

public class Restaurants {
	protected int restaurantId;
	protected String restaurantName;
	protected String restaurantDescription;
	protected String menu;
	protected String openHours;
	protected String street1;
	protected String street2;
	protected String city;
	protected String state;
	protected String zipCode;
	protected boolean isActive;
	protected CuisineType cuisineType;
	protected Companies company;

	public enum CuisineType {
		AFRICAN, AMERICAN, ASIAN, EUROPEAN, HISPANIC
	}

	public Restaurants(int restaurantId, String restaurantName, String restaurantDescription, String menu,
			String openHours, String street1, String street2, String city, String state, String zipCode,
			boolean isActive, CuisineType cuisineType, Companies company) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantDescription = restaurantDescription;
		this.menu = menu;
		this.openHours = openHours;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.isActive = isActive;
		this.cuisineType = cuisineType;
		this.company = company;
	}

	public Restaurants(String restaurantName, String restaurantDescription, String menu, String openHours,
			String street1, String street2, String city, String state, String zipCode, boolean isActive,
			CuisineType cuisineType, Companies company) {
		this.restaurantName = restaurantName;
		this.restaurantDescription = restaurantDescription;
		this.menu = menu;
		this.openHours = openHours;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.isActive = isActive;
		this.cuisineType = cuisineType;
		this.company = company;
	}

	public Restaurants(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantDescription() {
		return restaurantDescription;
	}

	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public CuisineType getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(CuisineType cuisineType) {
		this.cuisineType = cuisineType;
	}

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}
}