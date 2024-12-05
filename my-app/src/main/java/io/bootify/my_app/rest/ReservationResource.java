package io.bootify.my_app.rest;

import io.bootify.my_app.model.ReservationDTO;
import io.bootify.my_app.service.ReservationService;
import io.bootify.my_app.util.ReferencedException;
import io.bootify.my_app.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationResource {

    private final ReservationService reservationService;

    public ReservationResource(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservation(
            @PathVariable(name = "reservationId") final Integer reservationId) {
        return ResponseEntity.ok(reservationService.get(reservationId));
    }

    @PostMapping
    public ResponseEntity<Integer> createReservation(
            @RequestBody @Valid final ReservationDTO reservationDTO) {
        final Integer createdReservationId = reservationService.create(reservationDTO);
        return new ResponseEntity<>(createdReservationId, HttpStatus.CREATED);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Integer> updateReservation(
            @PathVariable(name = "reservationId") final Integer reservationId,
            @RequestBody @Valid final ReservationDTO reservationDTO) {
        reservationService.update(reservationId, reservationDTO);
        return ResponseEntity.ok(reservationId);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "reservationId") final Integer reservationId) {
        final ReferencedWarning referencedWarning = reservationService.getReferencedWarning(reservationId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        reservationService.delete(reservationId);
        return ResponseEntity.noContent().build();
    }

}
