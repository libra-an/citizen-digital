package com.tekseries.server.core.user.notification.event;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {

    // ai phụ trách các event like comment,... thì nhớ đẩy ra thông báo cho NotificationEvent này nhé.
    private final String userId;
    private final String type;
    private final String content;
    private final String relatedId;

    public NotificationEvent(Object source, String userId, String type, String content, String relatedId) {
        super(source);
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.relatedId = relatedId;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getRelatedId() {
        return relatedId;
    }
}