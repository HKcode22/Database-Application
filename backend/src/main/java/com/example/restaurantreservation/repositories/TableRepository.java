package main.java.com.example.restaurantreservation.repositories;

import main.java.com.example.restaurantreservation.models.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {

    // Find tables by restaurant ID using native SQL
    @Query(value = "SELECT * FROM Tables WHERE restaurant_id = :restaurantId", nativeQuery = true)
    List<RestaurantTable> findTablesByRestaurantId(int restaurantId);

    // Add new table using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Tables (restaurant_id, seats) VALUES (:restaurantId, :seats)", nativeQuery = true)
    void addTable(int restaurantId, int seats);

    // Update table using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE Tables SET capacity = :capacity, status= :status WHERE table_id = :tableId", nativeQuery = true)
    int updateTable(int tableId, int capacity, String status);

    // Delete table using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Tables WHERE table_id = :tableId", nativeQuery = true)
    void deleteTable(int tableId);
}
