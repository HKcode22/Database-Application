package io.bootify.my_app.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;


public class ReviewDTO {

    private Integer reviewId;

    @NotNull
    private Integer rating;

    private String reviewText;

    private OffsetDateTime createdAt;

    @NotNull
    private Integer customer;

    @NotNull
    private Integer restaurant;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(final Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(final String reviewText) {
        this.reviewText = reviewText;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(final Integer customer) {
        this.customer = customer;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Integer restaurant) {
        this.restaurant = restaurant;
    }

}
