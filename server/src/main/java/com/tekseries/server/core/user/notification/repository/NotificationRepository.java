package com.tekseries.server.core.user.notification.repository;

import com.tekseries.server.entity.Notification;
import com.tekseries.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> findByUserOrderByCreatedDateDesc(User user);

}
