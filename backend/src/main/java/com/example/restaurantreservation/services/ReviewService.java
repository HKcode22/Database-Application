package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.models.Review;
import main.java.com.example.restaurantreservation.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Get all reviews for a specific restaurant
    public List<Review> getReviewsByRestaurantId(int restaurantId) {
        return reviewRepository.findReviewsByRestaurantId(restaurantId);
    }

    // Add a new review
    public boolean addReview(int restaurantId, int customerId, int rating, String reviewText) {
        try {
            reviewRepository.addReview(restaurantId, customerId, rating, reviewText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Update an existing review
    public boolean updateReview(int reviewId, int rating, String reviewText) {
        return reviewRepository.updateReview(reviewId, rating, reviewText) > 0;
    }

    // Delete a review
    public boolean deleteReview(int reviewId) {
        try {
            reviewRepository.deleteReview(reviewId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
