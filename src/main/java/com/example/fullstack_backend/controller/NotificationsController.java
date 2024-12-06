package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Notification;
import com.example.fullstack_backend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {

    private final NotificationService notificationsService;

    @Autowired
    public NotificationsController(NotificationService notificationsService) {
        this.notificationsService = notificationsService;
    }

    // Get all notifications for a user
    @GetMapping("/user/{userId}")
    public List<Notification> getNotifications(@PathVariable int userId) {
        return notificationsService.getNotificationsByUserId(userId);
    }

    // Create a new notification
    @PostMapping("/create")
    public void createNotification(@RequestParam int userId, @RequestParam String message) {
        notificationsService.createNotification(userId, message);
    }

    // Mark notification as read
    @PostMapping("/markAsRead/{notificationId}")
    public void markNotificationAsRead(@PathVariable int notificationId) {
        notificationsService.markNotificationAsRead(notificationId);
    }

    // Delete notification
    @DeleteMapping("/delete/{notificationId}")
    public void deleteNotification(@PathVariable int notificationId) {
        notificationsService.deleteNotification(notificationId);
    }
}
