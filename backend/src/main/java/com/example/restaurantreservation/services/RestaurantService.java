package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.models.Restaurant;
import main.java.com.example.restaurantreservation.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Get all restaurants using SQL
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAllRestaurants();
    }

    // Get restaurant by ID using SQL
    public Restaurant getRestaurantById(int id) {
        return restaurantRepository.findRestaurantById(id);
    }

    // Add new restaurant using SQL
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantRepository.addRestaurant(restaurant.getName(), (restaurant.getStreetAddress() + restaurant.getCity()+ restaurant.getState()+ restaurant.getZipCode()), restaurant.getCategoryId());
        return restaurant;
    }

    public Restaurant getRestaurantDetails(int restaurantId) {
        return restaurantRepository.findRestaurantById(restaurantId);
    }

    // Update restaurant using SQL
    public boolean updateRestaurant(int id, Restaurant updatedRestaurant) {
        return restaurantRepository.updateRestaurant(
                id,
                updatedRestaurant.getName(),
                updatedRestaurant.getStreetAddress(),
                updatedRestaurant.getCity(),
                updatedRestaurant.getState(),
                updatedRestaurant.getZipCode()
        ) > 0;
    }

    // Delete restaurant using SQL
    public boolean deleteRestaurant(int id) {
        restaurantRepository.deleteRestaurant(id);
        return true;
    }
}
