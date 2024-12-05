package io.bootify.my_app.service;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.ReservationHistory;
import io.bootify.my_app.domain.Restaurant;
import io.bootify.my_app.model.ReservationDTO;
import io.bootify.my_app.repos.CustomerRepository;
import io.bootify.my_app.repos.ReservationHistoryRepository;
import io.bootify.my_app.repos.ReservationRepository;
import io.bootify.my_app.repos.RestaurantRepository;
import io.bootify.my_app.util.NotFoundException;
import io.bootify.my_app.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReservationHistoryRepository reservationHistoryRepository;

    public ReservationService(final ReservationRepository reservationRepository,
            final CustomerRepository customerRepository,
            final RestaurantRepository restaurantRepository,
            final ReservationHistoryRepository reservationHistoryRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.reservationHistoryRepository = reservationHistoryRepository;
    }

    public List<ReservationDTO> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll(Sort.by("reservationId"));
        return reservations.stream()
                .map(reservation -> mapToDTO(reservation, new ReservationDTO()))
                .toList();
    }

    public ReservationDTO get(final Integer reservationId) {
        return reservationRepository.findById(reservationId)
                .map(reservation -> mapToDTO(reservation, new ReservationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReservationDTO reservationDTO) {
        final Reservation reservation = new Reservation();
        mapToEntity(reservationDTO, reservation);
        return reservationRepository.save(reservation).getReservationId();
    }

    public void update(final Integer reservationId, final ReservationDTO reservationDTO) {
        final Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservationDTO, reservation);
        reservationRepository.save(reservation);
    }

    public void delete(final Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    private ReservationDTO mapToDTO(final Reservation reservation,
            final ReservationDTO reservationDTO) {
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setReservationTime(reservation.getReservationTime());
        reservationDTO.setPartySize(reservation.getPartySize());
        reservationDTO.setTimeCreated(reservation.getTimeCreated());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setCustomer(reservation.getCustomer() == null ? null : reservation.getCustomer().getCustomerId());
        reservationDTO.setRestaurant(reservation.getRestaurant() == null ? null : reservation.getRestaurant().getRestaurantId());
        return reservationDTO;
    }

    private Reservation mapToEntity(final ReservationDTO reservationDTO,
            final Reservation reservation) {
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setReservationTime(reservationDTO.getReservationTime());
        reservation.setPartySize(reservationDTO.getPartySize());
        reservation.setTimeCreated(reservationDTO.getTimeCreated());
        reservation.setStatus(reservationDTO.getStatus());
        final Customer customer = reservationDTO.getCustomer() == null ? null : customerRepository.findById(reservationDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        reservation.setCustomer(customer);
        final Restaurant restaurant = reservationDTO.getRestaurant() == null ? null : restaurantRepository.findById(reservationDTO.getRestaurant())
                .orElseThrow(() -> new NotFoundException("restaurant not found"));
        reservation.setRestaurant(restaurant);
        return reservation;
    }

    public ReferencedWarning getReferencedWarning(final Integer reservationId) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(NotFoundException::new);
        final ReservationHistory reservationReservationHistory = reservationHistoryRepository.findFirstByReservation(reservation);
        if (reservationReservationHistory != null) {
            referencedWarning.setKey("reservation.reservationHistory.reservation.referenced");
            referencedWarning.addParam(reservationReservationHistory.getHistoryId());
            return referencedWarning;
        }
        return null;
    }

}
