package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.Restaurant;
import dto.RestaurantDTO;
import com.example.fullstack_backend.repository.RestaurantRepository;
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
    public void addRestaurant(RestaurantDTO restaurantDTO) {
        restaurantRepository.addRestaurant(
                restaurantDTO.getUserId(),
                restaurantDTO.getName(),
                restaurantDTO.getEmail(),
                restaurantDTO.getStreetAddress(),
                restaurantDTO.getCity(),
                restaurantDTO.getState(),
                restaurantDTO.getZipCode(),
                restaurantDTO.getCategoryId(),
                restaurantDTO.getPhoneNumber(),
                restaurantDTO.getOpeningHours()
        );
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
