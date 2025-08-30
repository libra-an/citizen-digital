package com.tekseries.server.core.user.notification.event;

import com.tekseries.server.core.user.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.tekseries.server.entity.User;

@Component
public class NotificationListener {

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        User user = new User();
        user.setId(event.getUserId());

        notificationService.createNotification(
                user,
                event.getType(),
                event.getContent(),
                event.getRelatedId()
        );
    }
}

