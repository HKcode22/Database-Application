package main.java.com.example.restaurantreservation.controllers;

import main.java.com.example.restaurantreservation.models.Restaurant;
import main.java.com.example.restaurantreservation.services.RestaurantService;
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

    // Add a new restaurant
    @PostMapping("/add")
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);
        if (addedRestaurant != null) {
            return new ResponseEntity<>("Restaurant added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to add restaurant", HttpStatus.BAD_REQUEST);
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
