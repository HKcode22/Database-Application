package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.Category;
import com.example.fullstack_backend.model.Restaurant;
import com.example.fullstack_backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantSearchService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> searchRestaurants(String location, String category, String name) {
        if (location != null && !location.isEmpty()) {
            location = "%" + location.toLowerCase() + "%";
        }
        if (category != null && !category.isEmpty()) {
            category = "%" + category.toLowerCase() + "%";
        }
        if (name != null && !name.isEmpty()) {
            name = "%" + name.toLowerCase() + "%";
        }

        List<Object[]> resultSet = restaurantRepository.findRestaurantsBySearchCriteria(location, category, name);
        List<Restaurant> restaurants = new ArrayList<>();

        for (Object[] result : resultSet) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId((Integer) result[0]);
            restaurant.setName((String) result[1]);
            restaurant.setEmail((String) result[2]);
            restaurant.setStreetAddress((String) result[3]);
            restaurant.setCity((String) result[4]);
            restaurant.setState((String) result[5]);
            restaurant.setZipCode((String) result[6]);
            restaurant.setPhoneNumber((String) result[7]);
            restaurant.setOpeningHours((String) result[8]);

            // Since category can be optional, set it accordingly
            if (result[9] != null) {
                Category categoryEntity = new Category();
                categoryEntity.setCategoryName((String) result[9]);
                restaurant.setCategory(categoryEntity);
            }

            restaurants.add(restaurant);
        }

        return restaurants;
    }
}
