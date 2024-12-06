package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Category;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findFirstByUser(User user);

    Restaurant findFirstByCategory(Category category);

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(r.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Restaurant> searchRestaurants(@Param("name") String name, @Param("location") String location);

}
