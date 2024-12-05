package main.java.com.example.restaurantreservation.repositories;

import main.java.com.example.restaurantreservation.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    // Find all notifications for a specific user
    @Query(value = "SELECT * FROM notifications WHERE user_id = :userId", nativeQuery = true)
    List<Notification> findNotificationsByUserId(int userId);

    // Mark a notification as read
    @Modifying
    @Transactional
    @Query(value = "UPDATE notifications SET status = 'read' WHERE notification_id = :notificationId", nativeQuery = true)
    void markAsRead(int notificationId);

    // Create a new notification for a user
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO notifications (user_id, message) VALUES (:userId, :message)", nativeQuery = true)
    void createNotification(int userId, String message);

    // Delete a notification by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM notifications WHERE notification_id = :notificationId", nativeQuery = true)
    void deleteNotification(int notificationId);
}
