package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Category;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.User;
import io.bootify.my_app.model.RestaurantDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findFirstByUser(User user);

    Restaurant findFirstByCategory(Category category);
}
