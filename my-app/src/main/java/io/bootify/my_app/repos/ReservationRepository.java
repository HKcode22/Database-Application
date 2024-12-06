package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findFirstByCustomer(Customer customer);

    Reservation findFirstByRestaurant(Restaurant restaurant);

    @Query("SELECT r FROM Reservation r WHERE r.customer = :customer")
    List<Reservation> findReservationsByCustomer(@Param("customer") Customer customer);
    
    
}
