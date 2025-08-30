package com.tekseries.server.core.user.notification.service;

import com.tekseries.server.core.admin.dashboardUser.user.service.UserService;
import com.tekseries.server.core.user.notification.model.response.NotificationResponse;
import com.tekseries.server.core.user.notification.repository.NotificationRepository;
import com.tekseries.server.entity.Notification;
import com.tekseries.server.entity.User;
import com.tekseries.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public Notification createNotification(User user, String type, String content, String relatedId) {
        Notification n = new Notification();
        n.setId(UUID.randomUUID().toString());
        n.setUser(user);
        n.setType(type);
        n.setContent(content);
        n.setRelatedId(relatedId);
        n.setIsRead(false);
        n.setCreatedDate(System.currentTimeMillis());
        return notificationRepository.save(n);
    }

    public List<NotificationResponse> getUserNotifications(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return notificationRepository.findByUserOrderByCreatedDateDesc(user)
                .stream()
                .map(n -> new NotificationResponse(
                        n.getId(),
                        n.getType(),
                        n.getContent(),
                        n.getRelatedId(),
                        n.getIsRead(),
                        n.getCreatedDate() != null ?
                                Instant.ofEpochMilli(n.getCreatedDate())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDateTime()
                                : null
                ))
                .collect(Collectors.toList());
    }



    public void markAsRead(String notificationId) {
        notificationRepository.findById(notificationId).ifPresent(n -> {
            n.setIsRead(true);
            notificationRepository.save(n);
        });
    }
}

