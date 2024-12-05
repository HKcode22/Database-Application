package com.example.restaurantreservation.repositories;

import com.example.restaurantreservation.models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer> {

    // Find tables by restaurant ID using native SQL
    @Query(value = "SELECT * FROM tables WHERE restaurant_id = :restaurantId", nativeQuery = true)
    List<Tables> findTablesByRestaurantId(int restaurantId);

    // Add new table using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tables (restaurant_id, seats) VALUES (:restaurantId, :seats)", nativeQuery = true)
    void addTable(int restaurantId, int seats);

    // Update table using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE tables SET seats = :seats WHERE table_id = :tableId", nativeQuery = true)
    int updateTable(int tableId, int seats);

    // Delete table using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tables WHERE table_id = :tableId", nativeQuery = true)
    void deleteTable(int tableId);
}
