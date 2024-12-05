package com.example.restaurantreservation.controllers;

import com.example.restaurantreservation.services.RestaurantService;
import com.example.restaurantreservation.models.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    //Get restaurant details
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
/*
    //Update restaurant details
    @PutMapping("/{restaurantId}/update")
    public ResponseEntity<String> updateRestaurant(@PathVariable int restaurantId,
                                                   @RequestParam String name,
                                                   @RequestParam String address,
                                                   @RequestParam String openingHours) {
        boolean success = restaurantService.updateRestaurant(restaurantId, name, address, openingHours);
        if (success) {
            return new ResponseEntity<>("Restaurant details updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update restaurant details", HttpStatus.BAD_REQUEST);
    }

    //Modify reservation status
    @PutMapping("/{restaurantId}/reservations/{reservationId}/modify")
    public ResponseEntity<String> modifyReservation(@PathVariable int restaurantId,
                                                    @PathVariable int reservationId,
                                                    @RequestParam String status) {
        boolean success = restaurantService.updateRestaurant(restaurantId, reservationId, status);
        if (success) {
            return new ResponseEntity<>("Reservation status updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error updating reservation status", HttpStatus.BAD_REQUEST);
    }
 
    //Cancel a reservation
    @DeleteMapping("/{restaurantId}/reservations/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable int restaurantId, @PathVariable int reservationId) {
        boolean success = restaurantService.cancelReservation(restaurantId, reservationId);
        if (success) {
            return new ResponseEntity<>("Reservation cancelled", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error cancelling reservation", HttpStatus.BAD_REQUEST);
    }
*/
}
