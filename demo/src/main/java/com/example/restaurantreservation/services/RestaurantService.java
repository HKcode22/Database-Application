package com.example.restaurantreservation.services;

import com.example.restaurantreservation.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurantreservation.models.Restaurant;

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

    public List<Restaurant> searchRestaurants(String location, String category, String name) {
        return restaurantRepository.searchByCriteria(location, category, name);
    }
    
    // Add new restaurant using SQL
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantRepository.addRestaurant(restaurant.getName(), restaurant.getStreetAddress(), restaurant.getCategoryId());
        return restaurant;
    }

    // Update restaurant using SQL
    public boolean updateRestaurant(int id, Restaurant updatedRestaurant) {
        return restaurantRepository.updateRestaurant(id, updatedRestaurant.getName(), updatedRestaurant.getStreetAddress()) > 0;
    }

    // Delete restaurant using SQL
    public boolean deleteRestaurant(int id) {
        restaurantRepository.deleteRestaurant(id);
        return true;
    }
}