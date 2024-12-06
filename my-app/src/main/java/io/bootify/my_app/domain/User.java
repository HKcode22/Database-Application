package io.bootify.my_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, name = "\"role\"")
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Customer> userCustomers;

    @OneToMany(mappedBy = "user")
    private Set<Restaurant> userRestaurants;

    @OneToMany(mappedBy = "user")
    private Set<Notification> userNotifications;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public Set<Customer> getUserCustomers() {
        return userCustomers;
    }

    public void setUserCustomers(final Set<Customer> userCustomers) {
        this.userCustomers = userCustomers;
    }

    public Set<Restaurant> getUserRestaurants() {
        return userRestaurants;
    }

    public void setUserRestaurants(final Set<Restaurant> userRestaurants) {
        this.userRestaurants = userRestaurants;
    }

    public Set<Notification> getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(final Set<Notification> userNotifications) {
        this.userNotifications = userNotifications;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
