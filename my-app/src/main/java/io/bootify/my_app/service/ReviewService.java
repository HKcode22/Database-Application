package io.bootify.my_app.service;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.Review;
import io.bootify.my_app.model.ReviewDTO;
import io.bootify.my_app.repos.CustomerRepository;
import io.bootify.my_app.repos.RestaurantRepository;
import io.bootify.my_app.repos.ReviewRepository;
import io.bootify.my_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewService(final ReviewRepository reviewRepository,
            final CustomerRepository customerRepository,
            final RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<ReviewDTO> findAll() {
        final List<Review> reviews = reviewRepository.findAll(Sort.by("reviewId"));
        return reviews.stream()
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .toList();
    }

    public ReviewDTO get(final Integer reviewId) {
        return reviewRepository.findById(reviewId)
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReviewDTO reviewDTO) {
        final Review review = new Review();
        mapToEntity(reviewDTO, review);
        return reviewRepository.save(review).getReviewId();
    }

    public void update(final Integer reviewId, final ReviewDTO reviewDTO) {
        final Review review = reviewRepository.findById(reviewId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reviewDTO, review);
        reviewRepository.save(review);
    }

    public void delete(final Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private ReviewDTO mapToDTO(final Review review, final ReviewDTO reviewDTO) {
        reviewDTO.setReviewId(review.getReviewId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setReviewText(review.getReviewText());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        reviewDTO.setCustomer(review.getCustomer() == null ? null : review.getCustomer().getCustomerId());
        reviewDTO.setRestaurant(review.getRestaurant() == null ? null : review.getRestaurant().getRestaurantId());
        return reviewDTO;
    }

    private Review mapToEntity(final ReviewDTO reviewDTO, final Review review) {
        review.setRating(reviewDTO.getRating());
        review.setReviewText(reviewDTO.getReviewText());
        review.setCreatedAt(reviewDTO.getCreatedAt());
        final Customer customer = reviewDTO.getCustomer() == null ? null : customerRepository.findById(reviewDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        review.setCustomer(customer);
        final Restaurant restaurant = reviewDTO.getRestaurant() == null ? null : restaurantRepository.findById(reviewDTO.getRestaurant())
                .orElseThrow(() -> new NotFoundException("restaurant not found"));
        review.setRestaurant(restaurant);
        return review;
    }

    

}
