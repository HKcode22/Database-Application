package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Restaurant;
import com.example.fullstack_backend.services.RestaurantSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class RestaurantSearchController {

    @Autowired
    private RestaurantSearchService restaurantSearchService;

    @GetMapping("/restaurants")
    public ResponseEntity<?> searchRestaurants(@RequestParam String location,
                                               @RequestParam(required = false) String category,
                                               @RequestParam(required = false) String name) {
        try {
            List<Restaurant> restaurants = restaurantSearchService.searchRestaurants(location, category, name);
            if (restaurants.isEmpty()) {
                return new ResponseEntity<>("No restaurants found matching the criteria.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
