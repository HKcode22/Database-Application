package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findFirstByCustomer(Customer customer);

    Reservation findFirstByRestaurant(Restaurant restaurant);

}
