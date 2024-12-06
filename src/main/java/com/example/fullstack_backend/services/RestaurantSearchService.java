package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.Restaurant;
import com.example.fullstack_backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantSearchService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Search for restaurants based on location, category, and name
    public String searchRestaurants(String location, String category, String name) {
        // Fetch all restaurants from the repository
        List<Restaurant> restaurants = restaurantRepository.findAll();

        // Filter restaurants based on search criteria
        List<Restaurant> filteredRestaurants = restaurants.stream()
                .filter(restaurant -> matchesSearchCriteria(restaurant, location, category, name))
                .collect(Collectors.toList());

        // If no matching restaurants found, return a meaningful message
        if (filteredRestaurants.isEmpty()) {
            return "No restaurants found matching the criteria.";
        }

        // Build a string of search results
        StringBuilder results = new StringBuilder("Matching Restaurants:\n");
        for (Restaurant restaurant : filteredRestaurants) {
            results.append("Restaurant Name: ").append(restaurant.getName())
                    .append("\nLocation: ").append(restaurant.getStreetAddress())
                    .append(", ").append(restaurant.getCity())
                    .append(", ").append(restaurant.getState())
                    .append("\nCategory: ").append(restaurant.getCategory().getCategoryName())
                    .append("\n\n");
        }

        return results.toString();
    }

    // Helper method to filter restaurants based on criteria
    private boolean matchesSearchCriteria(Restaurant restaurant, String location, String category, String name) {
        boolean matchesLocation = location == null ||
                (restaurant.getStreetAddress() != null && restaurant.getStreetAddress().toLowerCase().contains(location.toLowerCase())) ||
                (restaurant.getCity() != null && restaurant.getCity().toLowerCase().contains(location.toLowerCase())) ||
                (restaurant.getState() != null && restaurant.getState().toLowerCase().contains(location.toLowerCase()));

        boolean matchesCategory = category == null ||
                (restaurant.getCategory() != null && restaurant.getCategory().getCategoryName().equalsIgnoreCase(category));

        boolean matchesName = name == null ||
                (restaurant.getName() != null && restaurant.getName().equalsIgnoreCase(name));

        return matchesLocation && matchesCategory && matchesName;
    }
}
