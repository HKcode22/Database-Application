package io.bootify.my_app.service;

import io.bootify.my_app.domain.Customer;
import io.bootify.my_app.domain.Reservation;
import io.bootify.my_app.domain.ReservationHistory;
import io.bootify.my_app.model.ReservationHistoryDTO;
import io.bootify.my_app.repos.CustomerRepository;
import io.bootify.my_app.repos.ReservationHistoryRepository;
import io.bootify.my_app.repos.ReservationRepository;
import io.bootify.my_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationHistoryService {

    private final ReservationHistoryRepository reservationHistoryRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    public ReservationHistoryService(
            final ReservationHistoryRepository reservationHistoryRepository,
            final CustomerRepository customerRepository,
            final ReservationRepository reservationRepository) {
        this.reservationHistoryRepository = reservationHistoryRepository;
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationHistoryDTO> findAll() {
        final List<ReservationHistory> reservationHistories = reservationHistoryRepository.findAll(Sort.by("historyId"));
        return reservationHistories.stream()
                .map(reservationHistory -> mapToDTO(reservationHistory, new ReservationHistoryDTO()))
                .toList();
    }

    public ReservationHistoryDTO get(final Integer historyId) {
        return reservationHistoryRepository.findById(historyId)
                .map(reservationHistory -> mapToDTO(reservationHistory, new ReservationHistoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReservationHistoryDTO reservationHistoryDTO) {
        final ReservationHistory reservationHistory = new ReservationHistory();
        mapToEntity(reservationHistoryDTO, reservationHistory);
        return reservationHistoryRepository.save(reservationHistory).getHistoryId();
    }

    public void update(final Integer historyId, final ReservationHistoryDTO reservationHistoryDTO) {
        final ReservationHistory reservationHistory = reservationHistoryRepository.findById(historyId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservationHistoryDTO, reservationHistory);
        reservationHistoryRepository.save(reservationHistory);
    }

    public void delete(final Integer historyId) {
        reservationHistoryRepository.deleteById(historyId);
    }

    private ReservationHistoryDTO mapToDTO(final ReservationHistory reservationHistory,
            final ReservationHistoryDTO reservationHistoryDTO) {
        reservationHistoryDTO.setHistoryId(reservationHistory.getHistoryId());
        reservationHistoryDTO.setCustomer(reservationHistory.getCustomer() == null ? null : reservationHistory.getCustomer().getCustomerId());
        reservationHistoryDTO.setReservation(reservationHistory.getReservation() == null ? null : reservationHistory.getReservation().getReservationId());
        return reservationHistoryDTO;
    }

    private ReservationHistory mapToEntity(final ReservationHistoryDTO reservationHistoryDTO,
            final ReservationHistory reservationHistory) {
        final Customer customer = reservationHistoryDTO.getCustomer() == null ? null : customerRepository.findById(reservationHistoryDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        reservationHistory.setCustomer(customer);
        final Reservation reservation = reservationHistoryDTO.getReservation() == null ? null : reservationRepository.findById(reservationHistoryDTO.getReservation())
                .orElseThrow(() -> new NotFoundException("reservation not found"));
        reservationHistory.setReservation(reservation);
        return reservationHistory;
    }

}
