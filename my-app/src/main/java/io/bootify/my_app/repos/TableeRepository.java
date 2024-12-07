package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.domain.Tablee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TableeRepository extends JpaRepository<Tablee, Integer> {

    Tablee findFirstByRestaurant(Restaurant restaurant);

}
