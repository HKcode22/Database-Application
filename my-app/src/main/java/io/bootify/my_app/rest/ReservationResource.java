package io.bootify.my_app.rest;

import io.bootify.my_app.model.ReservationDTO;
import io.bootify.my_app.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource {

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/user/{customerId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(reservationService.findReservationsByCustomer(customerId));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Integer> createReservation(@RequestBody @Valid ReservationDTO reservationDTO) {
        return new ResponseEntity<>(reservationService.create(reservationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable Integer reservationId,
                                                   @RequestBody @Valid ReservationDTO reservationDTO) {
        reservationService.update(reservationId, reservationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer reservationId) {
        reservationService.delete(reservationId);
        return ResponseEntity.noContent().build();
    }
}
