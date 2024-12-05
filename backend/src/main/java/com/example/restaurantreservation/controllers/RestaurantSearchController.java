package main.java.com.example.restaurantreservation.controllers;

import main.java.com.example.restaurantreservation.services.RestaurantSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class RestaurantSearchController {

    @Autowired
    private RestaurantSearchService restaurantSearchService;

    // Search for restaurants by location, category, or name
    @GetMapping("/restaurants")
    public ResponseEntity<String> searchRestaurants(@RequestParam String location,
                                                    @RequestParam(required = false) String category,
                                                    @RequestParam(required = false) String name) {
        // Validate the required parameter
        if (location == null || location.trim().isEmpty()) {
            return new ResponseEntity<>("Location parameter is required.", HttpStatus.BAD_REQUEST);
        }

        // Get search results
        String results = restaurantSearchService.searchRestaurants(location, category, name);

        // Return appropriate response based on the results
        if (results.contains("No restaurants found")) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
