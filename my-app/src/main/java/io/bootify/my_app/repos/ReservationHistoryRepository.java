package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Integer> {

    ReservationHistory findFirstByCustomer(Customer customer);

    ReservationHistory findFirstByReservation(Reservation reservation);

}
