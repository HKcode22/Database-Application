package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.models.Notification;
import main.java.com.example.restaurantreservation.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationsRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    // Get all notifications for a specific user
    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationsRepository.findNotificationsByUserId(userId);
    }

    // Create a new notification for a user
    public void createNotification(int userId, String message) {
        notificationsRepository.createNotification(userId, message);
    }

    // Mark a notification as read
    public void markNotificationAsRead(int notificationId) {
        notificationsRepository.markAsRead(notificationId);
    }

    // Delete a notification by ID
    public void deleteNotification(int notificationId) {
        notificationsRepository.deleteNotification(notificationId);
    }
}
