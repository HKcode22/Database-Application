package com.example.restaurantreservation.services;

import com.example.restaurantreservation.models.Review;
import com.example.restaurantreservation.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByRestaurantId(int restaurantId) {
        return reviewRepository.findReviewsByRestaurantId(restaurantId);
    }

    public void addReview(int restaurantId, int customerId, int rating, String comment) {
        reviewRepository.addReview(restaurantId, customerId, rating, comment);
    }

    public boolean updateReview(int reviewId, int rating, String comment) {
        return reviewRepository.updateReview(reviewId, rating, comment) > 0;
    }

    public boolean deleteReview(int reviewId) {
        reviewRepository.deleteReview(reviewId);
        return true;
    }
}
