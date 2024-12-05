package com.example.restaurantreservation.repositories;

import com.example.restaurantreservation.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Find reviews by restaurant ID using native SQL
    @Query(value = "SELECT * FROM reviews WHERE restaurant_id = :restaurantId", nativeQuery = true)
    List<Review> findReviewsByRestaurantId(int restaurantId);

    // Add a new review using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reviews (restaurant_id, customer_id, rating, comment) VALUES (:restaurantId, :customerId, :rating, :comment)", nativeQuery = true)
    void addReview(int restaurantId, int customerId, int rating, String comment);

    // Update review using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE reviews SET rating = :rating, comment = :comment WHERE review_id = :reviewId", nativeQuery = true)
    int updateReview(int reviewId, int rating, String comment);

    // Delete review using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reviews WHERE review_id = :reviewId", nativeQuery = true)
    void deleteReview(int reviewId);
}
