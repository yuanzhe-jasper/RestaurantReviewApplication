package restaurantReview.tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import restaurantReview.dal.CompaniesDao;
import restaurantReview.dal.CreditCardsDao;
import restaurantReview.dal.FoodCartRestaurantsDao;
import restaurantReview.dal.RecommendationsDao;
import restaurantReview.dal.ReservationsDao;
import restaurantReview.dal.RestaurantsDao;
import restaurantReview.dal.ReviewsDao;
import restaurantReview.dal.SitDownRestaurantsDao;
import restaurantReview.dal.TakeOutRestaurantsDao;
import restaurantReview.dal.UsersDao;
import restaurantReview.model.Companies;
import restaurantReview.model.CreditCards;
import restaurantReview.model.FoodCartRestaurant;
import restaurantReview.model.Recommendations;
import restaurantReview.model.Reservations;
import restaurantReview.model.Restaurants;
import restaurantReview.model.Reviews;
import restaurantReview.model.SitDownRestaurant;
import restaurantReview.model.TakeOutRestaurant;
import restaurantReview.model.Users;

public class Inserter {

	public static void main(String[] args) throws SQLException {

		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		CompaniesDao companyDao = CompaniesDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		ReservationsDao reservationsDao = ReservationsDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		FoodCartRestaurantsDao foodCartRestaurantsDao = FoodCartRestaurantsDao.getInstance();
		SitDownRestaurantsDao sitDownRestaurantsDao = SitDownRestaurantsDao.getInstance();
		TakeOutRestaurantsDao takeOutRestaurantsDao = TakeOutRestaurantsDao.getInstance();

		// INSERT objects from our model.
		Users user1 = new Users("user1", "123", "li", "li", "123@", "123");
		Users user2 = new Users("user2", "123", "li", "li", "123@", "123");
		Users user3 = new Users("user3;", "123", "li", "li", "123@", "123");
		Companies company1 = new Companies("company1", "tech");
		Companies company2 = new Companies("company2", "tech");
		Companies company3 = new Companies("company3", "tech");

//		long cardNumber, Date expiration, Users user
		CreditCards creditCard1 = new CreditCards(1234, new Date(), user1);
		CreditCards creditCard2 = new CreditCards(12345, new Date(), user1);
		Restaurants restaurant1 = new Restaurants(1, "res1", "good", "menu", "11am", "3rd", "301", "Seattle", "WA",
				"98119", true, Restaurants.CuisineType.AFRICAN, company1);
		Restaurants restaurant2 = new Restaurants(2, "res2", "good", "menu", "11am", "3rd", "301", "Seattle", "WA",
				"98119", true, Restaurants.CuisineType.AMERICAN, company2);
		Restaurants restaurant3 = new Restaurants(3, "res3", "good", "menu", "11am", "3rd", "301", "Seattle", "WA",
				"98119", true, Restaurants.CuisineType.ASIAN, company3);
		Restaurants restaurant4 = new Restaurants(4, "res1", "good", "menu", "11am", "3rd", "301", "Seattle", "WA",
				"98119", true, Restaurants.CuisineType.AFRICAN, company1);
		Restaurants restaurant5 = new Restaurants(5, "res2", "good", "menu", "11am", "3rd", "301", "Seattle", "WA",
				"98119", true, Restaurants.CuisineType.AMERICAN, company2);
		Restaurants restaurant6 = new Restaurants(6, "res3", "good", "menu", "11am", "3rd", "301", "Seattle", "WA",
				"98119", true, Restaurants.CuisineType.ASIAN, company3);
		SitDownRestaurant sitDownRestaurants1 = new SitDownRestaurant(1, "res1", "good", "menu", "11am", "3rd", "301",
				"Seattle", "WA", "98119", true, Restaurants.CuisineType.AFRICAN, company1, 5);
		SitDownRestaurant sitDownRestaurants2 = new SitDownRestaurant(4, "res1", "good", "menu", "11am", "3rd", "301",
				"Seattle", "WA", "98119", true, Restaurants.CuisineType.AFRICAN, company1, 5);
		FoodCartRestaurant foodCartRestaurants1 = new FoodCartRestaurant(2, "res2", "good", "menu", "11am", "3rd",
				"301", "Seattle", "WA", "98119", true, Restaurants.CuisineType.AMERICAN, company2, true);
		FoodCartRestaurant foodCartRestaurants2 = new FoodCartRestaurant(5, "res2", "good", "menu", "11am", "3rd",
				"301", "Seattle", "WA", "98119", true, Restaurants.CuisineType.AMERICAN, company2, true);
		TakeOutRestaurant takeOutRestaurants1 = new TakeOutRestaurant(3, "res3", "good", "menu", "11am", "3rd", "301",
				"Seattle", "WA", "98119", true, Restaurants.CuisineType.ASIAN, company3, 10);
		TakeOutRestaurant takeOutRestaurants2 = new TakeOutRestaurant(6, "res3", "good", "menu", "11am", "3rd", "301",
				"Seattle", "WA", "98119", true, Restaurants.CuisineType.ASIAN, company3, 10);
		Reviews review1 = new Reviews(3.0, new Date(), "good", user1, restaurant1);
		Reviews review2 = new Reviews(5.0, new Date(), "good", user2, restaurant1);
		Reviews review3 = new Reviews(5.0, new Date(), "good", user2, restaurant2);
		Recommendations recommendation1 = new Recommendations(user1, restaurant1);
		Recommendations recommendation2 = new Recommendations(user2, restaurant1);
		Recommendations recommendation3 = new Recommendations(user2, restaurant2);
		Reservations reservations1 = new Reservations(5, new Date(), new Date(), user1, sitDownRestaurants1);
		Reservations reservations2 = new Reservations(6, new Date(), new Date(), user1, sitDownRestaurants1);
		Reservations reservations3 = new Reservations(7, new Date(), new Date(), user1, sitDownRestaurants2);
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		creditCardsDao.create(creditCard1);
		creditCardsDao.create(creditCard2);
		companyDao.create(company1);
		companyDao.create(company2);
		companyDao.create(company3);
		restaurantsDao.create(restaurant1);
		restaurantsDao.create(restaurant2);
		restaurantsDao.create(restaurant3);
		restaurantsDao.create(restaurant4);
		restaurantsDao.create(restaurant5);
		restaurantsDao.create(restaurant6);
//
		sitDownRestaurantsDao.create(sitDownRestaurants1);
		sitDownRestaurantsDao.create(sitDownRestaurants2);
		takeOutRestaurantsDao.create(takeOutRestaurants1);
		takeOutRestaurantsDao.create(takeOutRestaurants2);
		foodCartRestaurantsDao.create(foodCartRestaurants1);
		foodCartRestaurantsDao.create(foodCartRestaurants2);
//
		reviewsDao.create(review1);
		reviewsDao.create(review2);
		reviewsDao.create(review3);

		recommendationsDao.create(recommendation1);
		recommendationsDao.create(recommendation2);
		recommendationsDao.create(recommendation3);
//
		reservationsDao.create(reservations1);
		reservationsDao.create(reservations2);
		reservationsDao.create(reservations3);

		// Read
		Users targetUser = usersDao.getUserByUserName("user1");
		System.out.println(targetUser.getEmail());

		CreditCards targetCard = creditCardsDao.getCreditCardByCardNumber(1234);
		System.out.println(targetCard.getExpiration());

		Companies targetCompany = companyDao.getCompanyByCompanyName("company1");
		System.out.println(targetCompany.getAbout());
//
		SitDownRestaurant targetSitDownRestaurant = sitDownRestaurantsDao.getSitDownRestaurantById(1);
		System.out.println(targetSitDownRestaurant.getCapacity());
//
		FoodCartRestaurant targetFoodCartRestaurants = foodCartRestaurantsDao.getFoodCartRestaurantById(2);
		System.out.println(targetFoodCartRestaurants.isLicensed());
//
		TakeOutRestaurant targetTakeOutRestaurant = takeOutRestaurantsDao.getTakeOutRestaurantById(3);
		System.out.println(targetTakeOutRestaurant.getMaxWaitTime());
//
		Reviews targetReview = reviewsDao.getReviewById(2);
		System.out.println(targetReview.getReviewId());
//
		Reservations targetReservation = reservationsDao.getReservationById(2);
		System.out.println(targetReservation.getReservationId());
//
		Recommendations targetRecommendation = recommendationsDao.getRecommendationById(2);
		System.out.println(targetRecommendation.getUser().getUserName());

		List<Reviews> reviewsList1 = reviewsDao.getReviewsByUserName("user2");
		for (Reviews r : reviewsList1) {
			System.out.println(r.getUser().getUserName());
		}
//
		List<Reviews> reviewsList2 = reviewsDao.getReviewsByRestaurantId(1);
		for (Reviews r : reviewsList2) {
			System.out.println(r.getUser().getUserName());
		}
//

		List<Recommendations> recommendationsList1 = recommendationsDao.getRecommendationsByRestaurantId(1);
		for (Recommendations r : recommendationsList1) {
			System.out.println(r.getRecommendationId());
		}

		List<Recommendations> recommendationsList2 = recommendationsDao.getRecommendationsByUserName("user2");
		for (Recommendations r : recommendationsList2) {
			System.out.println(r.getRecommendationId());
		}
//
		List<SitDownRestaurant> sitDownRestaurantList = sitDownRestaurantsDao
				.getSitDownRestaurantsByCompanyName("company1");
		for (SitDownRestaurant s : sitDownRestaurantList) {
			System.out.println(s.getRestaurantId());
		}
//
		List<FoodCartRestaurant> foodCartRestaurantList = foodCartRestaurantsDao
				.getFoodCartRestaurantsByCompanyName("company2");
		for (FoodCartRestaurant f : foodCartRestaurantList) {
			System.out.println(f.getRestaurantId());
		}

		List<TakeOutRestaurant> takeOutRestaurantList = takeOutRestaurantsDao
				.getTakeOutRestaurantsByCompanyName("company3");
		for (TakeOutRestaurant t : takeOutRestaurantList) {
			System.out.println(t.getRestaurantId());
		}

		List<Reservations> reservationList1 = reservationsDao.getReservationsBySitDownRestaurantId(1);
		for (Reservations r : reservationList1) {
			System.out.println(r.getReservationId());
		}

		List<Reservations> reservationList2 = reservationsDao.getReservationsByUserName("user1");
		for (Reservations r : reservationList2) {
			System.out.println(r.getReservationId());
		}

		// Update
		creditCardsDao.updateExpiration(creditCard1, new Date());

		// Delete
		reservationsDao.delete(reservations2);

		recommendationsDao.delete(recommendation1);

		reviewsDao.delete(review1);

		foodCartRestaurantsDao.delete(foodCartRestaurants1);

		sitDownRestaurantsDao.delete(sitDownRestaurants1);

		takeOutRestaurantsDao.delete(takeOutRestaurants1);

		restaurantsDao.delete(restaurant6);

		companyDao.delete(company3);

		creditCardsDao.delete(creditCard2);

		usersDao.delete(user3);

	}
}