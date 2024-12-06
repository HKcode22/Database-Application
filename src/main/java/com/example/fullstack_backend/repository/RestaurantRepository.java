package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Restaurant;
import dto.RestaurantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    // Fetch all restaurants using native SQL
    @Query(value = "SELECT * FROM Restaurants", nativeQuery = true)
    List<Restaurant> findAllRestaurants();

    // Get restaurant by ID using native SQL
    @Query(value = "SELECT * FROM Restaurants WHERE restaurant_id = :id", nativeQuery = true)
    Restaurant findRestaurantById(@Param("id") int id);

    // Add a new restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Restaurants (user_id, name, email, street_address, city, state, zip_code, category_id, phone_number, opening_hours) " +
            "VALUES (:#{#restaurant.userId}, :#{#restaurant.name}, :#{#restaurant.email}, :#{#restaurant.streetAddress}, " +
            ":#{#restaurant.city}, :#{#restaurant.state}, :#{#restaurant.zipCode}, :#{#restaurant.categoryId}, " +
            ":#{#restaurant.phoneNumber}, :#{#restaurant.openingHours})", nativeQuery = true)
    void addRestaurant(@Param("restaurant") RestaurantDTO restaurantDTO);

    // Update restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE Restaurants SET name = :name, street_address = :streetAddress, city = :city, state = :state, zip_code = :zip_code WHERE restaurant_id = :restaurantId", nativeQuery = true)
    int updateRestaurant(@Param("restaurantId") int restaurantId,
                         @Param("name") String name,
                         @Param("streetAddress") String streetAddress,
                         @Param("city") String city,
                         @Param("state") String state,
                         @Param("zip_code") String zipCode);

    // Delete restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Restaurants WHERE restaurant_id = :restaurantId", nativeQuery = true)
    void deleteRestaurant(@Param("restaurantId") int restaurantId);
}
