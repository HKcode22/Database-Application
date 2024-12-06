package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Review;
import com.example.fullstack_backend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Get all reviews for a specific restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Review>> getReviewsByRestaurant(@PathVariable int restaurantId) {
        List<Review> reviews = reviewService.getReviewsByRestaurantId(restaurantId);
        if (reviews != null && !reviews.isEmpty()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add a new review
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam int restaurantId,
                                            @RequestParam int customerId,
                                            @RequestParam int rating,
                                            @RequestParam String reviewText) {
        boolean success = reviewService.addReview(restaurantId, customerId, rating, reviewText);
        if (success) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to add review", HttpStatus.BAD_REQUEST);
    }

    // Update an existing review
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable int reviewId,
                                               @RequestParam int rating,
                                               @RequestParam String reviewText) {
        boolean success = reviewService.updateReview(reviewId, rating, reviewText);
        if (success) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update review", HttpStatus.BAD_REQUEST);
    }

    // Delete a review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable int reviewId) {
        boolean success = reviewService.deleteReview(reviewId);
        if (success) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete review", HttpStatus.BAD_REQUEST);
    }
}
