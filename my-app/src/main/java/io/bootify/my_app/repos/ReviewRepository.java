package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findFirstByCustomer(Customer customer);

    Review findFirstByRestaurant(Restaurant restaurant);

}
