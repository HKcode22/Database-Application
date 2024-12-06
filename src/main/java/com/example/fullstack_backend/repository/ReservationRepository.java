package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // Create reservation using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reservations (customer_id, restaurant_id, reservation_date, reservation_time, party_size) VALUES (:customerId, :restaurantId, :reservationDate, :reservationTime, :partySize)", nativeQuery = true)
    void createReservation(int customerId, int restaurantId, String reservationDate, String reservationTime, int partySize);

    // Get reservations by customer ID using native SQL
    @Query(value = "SELECT * FROM Reservations WHERE customer_id = :customerId", nativeQuery = true)
    List<Reservation> findReservationsByCustomerId(int customerId);

    // Update reservation using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE Reservations SET reservation_date = :newReservationDate, reservation_time = :newReservationTime WHERE reservation_id = :reservationId", nativeQuery = true)
    int updateReservation(int reservationId, String newReservationDate, String newReservationTime);

    // Cancel reservation using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reservations WHERE reservation_id = :reservationId", nativeQuery = true)
    void cancelReservation(int reservationId);
}
