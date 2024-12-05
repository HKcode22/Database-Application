package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Category;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findFirstByUser(User user);

    Restaurant findFirstByCategory(Category category);

}
