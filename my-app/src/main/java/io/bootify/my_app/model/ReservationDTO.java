package io.bootify.my_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;


public class ReservationDTO {

    private Integer reservationId;

    @NotNull
    private LocalDate reservationDate;

    @NotNull
    private LocalTime reservationTime;

    @NotNull
    private Integer partySize;

    private OffsetDateTime timeCreated;

    @Size(max = 255)
    private String status;

    @NotNull
    private Integer customer;

    @NotNull
    private Integer restaurant;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(final Integer reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(final LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(final LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(final Integer partySize) {
        this.partySize = partySize;
    }

    public OffsetDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(final OffsetDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(final Integer customer) {
        this.customer = customer;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Integer restaurant) {
        this.restaurant = restaurant;
    }

}
