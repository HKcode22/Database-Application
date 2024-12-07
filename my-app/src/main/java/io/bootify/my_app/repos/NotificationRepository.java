package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Notification;
import io.bootify.my_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findFirstByUser(User user);

}
