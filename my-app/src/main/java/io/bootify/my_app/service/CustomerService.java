package io.bootify.my_app.service;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.ReservationHistory;
import io.bootify.my_app.domain.Review;
import io.bootify.my_app.domain.User;
import io.bootify.my_app.model.CustomerDTO;
import io.bootify.my_app.repos.CustomerRepository;
import io.bootify.my_app.repos.ReservationHistoryRepository;
import io.bootify.my_app.repos.ReservationRepository;
import io.bootify.my_app.repos.ReviewRepository;
import io.bootify.my_app.repos.UserRepository;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationHistoryRepository reservationHistoryRepository;

    public CustomerService(final CustomerRepository customerRepository,
            final UserRepository userRepository, final ReservationRepository reservationRepository,
            final ReviewRepository reviewRepository,
            final ReservationHistoryRepository reservationHistoryRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
        this.reservationHistoryRepository = reservationHistoryRepository;
    }

    public List<CustomerDTO> findAll() {
        final List<Customer> customers = customerRepository.findAll(Sort.by("customerId"));
        return customers.stream()
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .toList();
    }

    public CustomerDTO get(final Integer customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        mapToEntity(customerDTO, customer);
        return customerRepository.save(customer).getCustomerId();
    }

    public void update(final Integer customerId, final CustomerDTO customerDTO) {
        final Customer customer = customerRepository.findById(customerId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(customerDTO, customer);
        customerRepository.save(customer);
    }

    public void delete(final Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    private CustomerDTO mapToDTO(final Customer customer, final CustomerDTO customerDTO) {
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setUser(customer.getUser() == null ? null : customer.getUser().getUserId());
        return customerDTO;
    }

    private Customer mapToEntity(final CustomerDTO customerDTO, final Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        final User user = customerDTO.getUser() == null ? null : userRepository.findById(customerDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        customer.setUser(user);
        return customer;
    }

    public ReferencedWarning getReferencedWarning(final Integer customerId) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Customer customer = customerRepository.findById(customerId)
                .orElseThrow(NotFoundException::new);
        final Reservation customerReservation = reservationRepository.findFirstByCustomer(customer);
        if (customerReservation != null) {
            referencedWarning.setKey("customer.reservation.customer.referenced");
            referencedWarning.addParam(customerReservation.getReservationId());
            return referencedWarning;
        }
        final Review customerReview = reviewRepository.findFirstByCustomer(customer);
        if (customerReview != null) {
            referencedWarning.setKey("customer.review.customer.referenced");
            referencedWarning.addParam(customerReview.getReviewId());
            return referencedWarning;
        }
        final ReservationHistory customerReservationHistory = reservationHistoryRepository.findFirstByCustomer(customer);
        if (customerReservationHistory != null) {
            referencedWarning.setKey("customer.reservationHistory.customer.referenced");
            referencedWarning.addParam(customerReservationHistory.getHistoryId());
            return referencedWarning;
        }
        return null;
    }

}
