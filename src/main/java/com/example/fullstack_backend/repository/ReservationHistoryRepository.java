package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Integer> {

    // Find reservation history by customer ID
    @Query(value = "SELECT * FROM reservation_history WHERE customer_id = :customerId", nativeQuery = true)
    List<ReservationHistory> findReservationHistoryByCustomerId(int customerId);

    // Add a reservation history record
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservation_history (customer_id, reservation_id) VALUES (:customerId, :reservationId)", nativeQuery = true)
    void addReservationHistory(int customerId, int reservationId);

    // Delete reservation history by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservation_history WHERE history_id = :historyId", nativeQuery = true)
    void deleteReservationHistory(int historyId);
}
