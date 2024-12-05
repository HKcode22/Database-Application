package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findFirstByUser(User user);

}
