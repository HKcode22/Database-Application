package io.bootify.my_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;


public class NotificationDTO {

    private Integer notificationId;

    private String message;

    private OffsetDateTime createdAt;

    @Size(max = 255)
    private String status;

    @NotNull
    private Integer user;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(final Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(final Integer user) {
        this.user = user;
    }

}
