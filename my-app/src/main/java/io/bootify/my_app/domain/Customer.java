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
public class Customer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(length = 100)
    private String name;

    @Column(length = 15)
    private String phoneNumber;

    

    @Column
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "customer")
    private Set<Reservation> customerReservations;

    @OneToMany(mappedBy = "customer")
    private Set<Review> customerReviews;

    @OneToMany(mappedBy = "customer")
    private Set<ReservationHistory> customerReservationHistories;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Set<Reservation> getCustomerReservations() {
        return customerReservations;
    }

    public void setCustomerReservations(final Set<Reservation> customerReservations) {
        this.customerReservations = customerReservations;
    }

    public Set<Review> getCustomerReviews() {
        return customerReviews;
    }

    public void setCustomerReviews(final Set<Review> customerReviews) {
        this.customerReviews = customerReviews;
    }

    public Set<ReservationHistory> getCustomerReservationHistories() {
        return customerReservationHistories;
    }

    public void setCustomerReservationHistories(
            final Set<ReservationHistory> customerReservationHistories) {
        this.customerReservationHistories = customerReservationHistories;
    }

}
