package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.models.Reservation;
import main.java.com.example.restaurantreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Create reservation using SQL
    public boolean createReservation(int customerId, int restaurantId, String reservationDate, String reservationTime, int partySize) {
        reservationRepository.createReservation(customerId, restaurantId, reservationDate, reservationTime, partySize);
        return true;
    }

    // Get reservations by customer ID using SQL
    public List<Reservation> getReservationsByCustomerId(int customerId) {
        return reservationRepository.findReservationsByCustomerId(customerId);
    }

    // Update reservation using SQL
    public boolean updateReservation(int reservationId, String newReservationDate, String newReservationTime) {
        return reservationRepository.updateReservation(reservationId, newReservationDate, newReservationTime) > 0;
    }

    // Cancel reservation using SQL
    public boolean cancelReservation(int reservationId) {
        reservationRepository.cancelReservation(reservationId);
        return true;
    }
}
