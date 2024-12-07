package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //Create new reservation
    @PostMapping("/create")
    public ResponseEntity<String> createReservation(@RequestBody Map<String, Object> payload) {
        try {
            int customerId = (int) payload.get("customerId");
            int restaurantId = (int) payload.get("restaurantId");
            String reservationDate = (String) payload.get("reservationDate");
            String reservationTime = (String) payload.get("reservationTime");
            int partySize = (int) payload.get("partySize");

            // Debugging: Log payload values
            System.out.println("Received payload: " + payload);

            boolean success = reservationService.createReservation(customerId, restaurantId, reservationDate, reservationTime, partySize);
            if (success) {
                return new ResponseEntity<>("Reservation created successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Error creating reservation", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid request payload", HttpStatus.BAD_REQUEST);
        }
    }



    //View customer's reservations
    @GetMapping("/{customerId}")
    public ResponseEntity<String> viewReservations(@PathVariable int customerId) {
        String reservations = reservationService.getReservationsByCustomerId(customerId).toString();
        if (reservations != null && !reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>("No reservations found", HttpStatus.NOT_FOUND);
    }

    //Modify a reservation
    @PutMapping("/{reservationId}/modify")
    public ResponseEntity<String> modifyReservation(@PathVariable int reservationId, 
                                                    @RequestParam String newReservationDate, 
                                                    @RequestParam String newReservationTime) {
        boolean success = reservationService.modifyReservation(reservationId, newReservationDate, newReservationTime);
        if (success) {
            return new ResponseEntity<>("Reservation modified successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error modifying reservation", HttpStatus.BAD_REQUEST);
    }

    //Cancel a reservation
    @DeleteMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable int reservationId) {
        boolean success = reservationService.cancelReservation(reservationId);
        if (success) {
            return new ResponseEntity<>("Reservation cancelled", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error cancelling reservation", HttpStatus.BAD_REQUEST);
    }
}
