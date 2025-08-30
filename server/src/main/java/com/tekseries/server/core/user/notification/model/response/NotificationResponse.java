package com.tekseries.server.core.user.notification.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private String id;
    private String type;
    private String content;
    private String relatedId;
    private boolean isRead;
    private LocalDateTime createdAt;
}

