package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Restaurant;
import dto.RestaurantDTO;
import com.example.fullstack_backend.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Get all restaurants
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        if (!restaurants.isEmpty()) {
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get restaurant details by ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantDetails(restaurantId);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        try {
            restaurantService.addRestaurant(restaurantDTO);
            return new ResponseEntity<>("Restaurant added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding restaurant: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update restaurant details
    @PutMapping("/{restaurantId}/update")
    public ResponseEntity<String> updateRestaurant(@PathVariable int restaurantId,
                                                   @RequestBody Restaurant updatedRestaurant) {
        boolean success = restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
        if (success) {
            return new ResponseEntity<>("Restaurant details updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update restaurant details", HttpStatus.BAD_REQUEST);
    }

    // Delete a restaurant
    @DeleteMapping("/{restaurantId}/delete")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int restaurantId) {
        boolean success = restaurantService.deleteRestaurant(restaurantId);
        if (success) {
            return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error deleting restaurant", HttpStatus.BAD_REQUEST);
    }
}
