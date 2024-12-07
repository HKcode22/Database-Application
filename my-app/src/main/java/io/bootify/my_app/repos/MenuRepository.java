package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Menu;
import io.bootify.my_app.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findFirstByRestaurant(Restaurant restaurant);

}
