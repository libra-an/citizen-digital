package com.tekseries.server.core.user.notification.controller;

import com.tekseries.server.core.user.notification.model.request.NotificationReadRequest;
import com.tekseries.server.core.user.notification.model.response.NotificationResponse;
import com.tekseries.server.core.user.notification.service.NotificationService;
import com.tekseries.server.infrastructure.constant.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MappingConstants.API_VERSION_PREFIX + MappingConstants.USER + "/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> getNotifications(@RequestParam String userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PostMapping("/read")
    public void markAsRead(@RequestBody NotificationReadRequest request) {
        notificationService.markAsRead(request.getNotificationId());
    }
}


