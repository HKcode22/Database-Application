package io.bootify.my_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.Set;


@Entity
public class Restaurant {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Column
    private String streetAddress;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(length = 10)
    private String zipCode;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 100)
    private String openingHours;

    @Column
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "restaurant")
    private Set<Reservation> restaurantReservations;

    @OneToMany(mappedBy = "restaurant")
    private Set<Tablee> restaurantTables;

    @OneToMany(mappedBy = "restaurant")
    private Set<Menu> restaurantMenus;

    @OneToMany(mappedBy = "restaurant")
    private Set<Review> restaurantReviews;

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(final Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(final String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(final String openingHours) {
        this.openingHours = openingHours;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Set<Reservation> getRestaurantReservations() {
        return restaurantReservations;
    }

    public void setRestaurantReservations(final Set<Reservation> restaurantReservations) {
        this.restaurantReservations = restaurantReservations;
    }

    public Set<Tablee> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(final Set<Tablee> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }

    public Set<Menu> getRestaurantMenus() {
        return restaurantMenus;
    }

    public void setRestaurantMenus(final Set<Menu> restaurantMenus) {
        this.restaurantMenus = restaurantMenus;
    }

    public Set<Review> getRestaurantReviews() {
        return restaurantReviews;
    }

    public void setRestaurantReviews(final Set<Review> restaurantReviews) {
        this.restaurantReviews = restaurantReviews;
    }

}
