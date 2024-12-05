package com.example.restaurantreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restaurantreservation.models.Restaurant;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    // Fetch all restaurants using native SQL
    @Query(value = "SELECT * FROM restaurant", nativeQuery = true)
    List<Restaurant> findAllRestaurants();

    // Get restaurant by ID using native SQL
    @Query(value = "SELECT * FROM restaurant WHERE restaurant_id = :id", nativeQuery = true)
    Restaurant findRestaurantById(int id);

    // Search restaurants by location, category, or name
    @Query(value = "SELECT * FROM restaurant WHERE address LIKE %:location% AND " +
    "(:category IS NULL OR category_id = :category) AND " +
    "(:name IS NULL OR name LIKE %:name%)", nativeQuery = true)
    List<Restaurant> searchByCriteria(String location, String category, String name);

    // Add new restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO restaurant (name, address, category_id) VALUES (:name, :address, :categoryId)", nativeQuery = true)
    void addRestaurant(String name, String address, int categoryId);

    // Update restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurant SET name = :name, address = :address WHERE restaurant_id = :restaurantId", nativeQuery = true)
    int updateRestaurant(int restaurantId, String name, String address);

    // Delete restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM restaurant WHERE restaurant_id = :restaurantId", nativeQuery = true)
    void deleteRestaurant(int restaurantId);
}
