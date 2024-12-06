package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Find reviews by restaurant ID using native SQL
    @Query(value = "SELECT * FROM Reviews WHERE restaurant_id = :restaurantId", nativeQuery = true)
    List<Review> findReviewsByRestaurantId(int restaurantId);

    // Add a new review using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reviews (restaurant_id, customer_id, rating, review_text) VALUES (:restaurantId, :customerId, :rating, :review_text)", nativeQuery = true)
    void addReview(int restaurantId, int customerId, int rating, String review_text);

    // Update review using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE Reviews SET rating = :rating, review_text = :review_text WHERE review_id = :reviewId", nativeQuery = true)
    int updateReview(int reviewId, int rating, String review_text);

    // Delete review using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reviews WHERE review_id = :reviewId", nativeQuery = true)
    void deleteReview(int reviewId);
}
