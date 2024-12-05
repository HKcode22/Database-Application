package io.bootify.my_app.rest;

import io.bootify.my_app.model.ReservationHistoryDTO;
import io.bootify.my_app.service.ReservationHistoryService;
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
@RequestMapping(value = "/api/reservationHistories", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationHistoryResource {

    private final ReservationHistoryService reservationHistoryService;

    public ReservationHistoryResource(final ReservationHistoryService reservationHistoryService) {
        this.reservationHistoryService = reservationHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationHistoryDTO>> getAllReservationHistories() {
        return ResponseEntity.ok(reservationHistoryService.findAll());
    }

    @GetMapping("/{historyId}")
    public ResponseEntity<ReservationHistoryDTO> getReservationHistory(
            @PathVariable(name = "historyId") final Integer historyId) {
        return ResponseEntity.ok(reservationHistoryService.get(historyId));
    }

    @PostMapping
    public ResponseEntity<Integer> createReservationHistory(
            @RequestBody @Valid final ReservationHistoryDTO reservationHistoryDTO) {
        final Integer createdHistoryId = reservationHistoryService.create(reservationHistoryDTO);
        return new ResponseEntity<>(createdHistoryId, HttpStatus.CREATED);
    }

    @PutMapping("/{historyId}")
    public ResponseEntity<Integer> updateReservationHistory(
            @PathVariable(name = "historyId") final Integer historyId,
            @RequestBody @Valid final ReservationHistoryDTO reservationHistoryDTO) {
        reservationHistoryService.update(historyId, reservationHistoryDTO);
        return ResponseEntity.ok(historyId);
    }

    @DeleteMapping("/{historyId}")
    public ResponseEntity<Void> deleteReservationHistory(
            @PathVariable(name = "historyId") final Integer historyId) {
        reservationHistoryService.delete(historyId);
        return ResponseEntity.noContent().build();
    }

}
