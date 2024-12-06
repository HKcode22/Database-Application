package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.ReservationHistory;
import com.example.fullstack_backend.services.ReservationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation-history")
public class ReservationHistoryController {

    private final ReservationHistoryService reservationHistoryService;

    @Autowired
    public ReservationHistoryController(ReservationHistoryService reservationHistoryService) {
        this.reservationHistoryService = reservationHistoryService;
    }

    // Get all reservation history for a customer
    @GetMapping("/customer/{customerId}")
    public List<ReservationHistory> getReservationHistory(@PathVariable int customerId) {
        return reservationHistoryService.getReservationHistoryByCustomerId(customerId);
    }

    // Create a new reservation history
    @PostMapping("/create")
    public void addReservationHistory(@RequestParam int customerId, @RequestParam int reservationId) {
        reservationHistoryService.addReservationHistory(customerId, reservationId);
    }

    // Delete reservation history by ID
    @DeleteMapping("/delete/{historyId}")
    public void deleteReservationHistory(@PathVariable int historyId) {
        reservationHistoryService.deleteReservationHistory(historyId);
    }
}
