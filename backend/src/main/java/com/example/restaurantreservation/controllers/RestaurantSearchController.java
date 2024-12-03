package com.example.restaurantreservation.controllers;

import com.example.restaurantreservation.services.RestaurantSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class RestaurantSearchController {

    @Autowired
    private RestaurantSearchService restaurantSearchService;

    //Search for restaurants by location, category, or name
    @GetMapping("/restaurants")
    public ResponseEntity<String> searchRestaurants(@RequestParam String location, 
                                                    @RequestParam(required = false) String category, 
                                                    @RequestParam(required = false) String name) {
        //get search results
        String results = restaurantSearchService.searchRestaurants(location, category, name);
        
        //Check if results are found
        if (results != null && !results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        
        //Return a not found response if no restaurants match the criteria
        return new ResponseEntity<>("No restaurants found matching the criteria", HttpStatus.NOT_FOUND);
    }
}
