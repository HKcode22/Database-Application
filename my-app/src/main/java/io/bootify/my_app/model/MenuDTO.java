package io.bootify.my_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;


public class MenuDTO {

    private Integer menuId;

    @NotNull
    @Size(max = 100)
    private String itemName;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal itemPrice;

    private String description;

    @NotNull
    private Integer restaurant;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(final Integer menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(final BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Integer restaurant) {
        this.restaurant = restaurant;
    }

}
