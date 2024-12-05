package io.bootify.my_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class TableeDTO {

    private Integer tableId;

    @NotNull
    private Integer capacity;

    @Size(max = 255)
    private String status;

    @NotNull
    private Integer restaurant;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(final Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Integer restaurant) {
        this.restaurant = restaurant;
    }

}
