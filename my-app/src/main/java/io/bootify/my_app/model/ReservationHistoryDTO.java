package io.bootify.my_app.model;

import jakarta.validation.constraints.NotNull;


public class ReservationHistoryDTO {

    private Integer historyId;

    @NotNull
    private Integer customer;

    @NotNull
    private Integer reservation;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(final Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(final Integer customer) {
        this.customer = customer;
    }

    public Integer getReservation() {
        return reservation;
    }

    public void setReservation(final Integer reservation) {
        this.reservation = reservation;
    }

}
