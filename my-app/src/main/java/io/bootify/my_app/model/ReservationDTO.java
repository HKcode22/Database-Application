package io.bootify.my_app.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {
    private Integer reservationId;
    private LocalDate reservationDate; // Updated from String to LocalDate
    private LocalTime reservationTime; // Updated from String to LocalTime
    private Integer partySize;
    private Integer customerId;
    private Integer restaurantId;

    // Getters and Setters
    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getReservationDate() { // Updated
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) { // Updated
        this.reservationDate = reservationDate;
    }

    public LocalTime getReservationTime() { // Updated
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) { // Updated
        this.reservationTime = reservationTime;
    }

    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
