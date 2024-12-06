package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.ReservationHistory;
import com.example.fullstack_backend.repository.ReservationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationHistoryService {

    private final ReservationHistoryRepository reservationHistoryRepository;

    @Autowired
    public ReservationHistoryService(ReservationHistoryRepository reservationHistoryRepository) {
        this.reservationHistoryRepository = reservationHistoryRepository;
    }

    // Get all reservation history for a specific customer
    public List<ReservationHistory> getReservationHistoryByCustomerId(int customerId) {
        return reservationHistoryRepository.findReservationHistoryByCustomerId(customerId);
    }

    // Add a new reservation history record
    public void addReservationHistory(int customerId, int reservationId) {
        reservationHistoryRepository.addReservationHistory(customerId, reservationId);
    }

    // Delete reservation history by ID
    public void deleteReservationHistory(int historyId) {
        reservationHistoryRepository.deleteReservationHistory(historyId);
    }
}
