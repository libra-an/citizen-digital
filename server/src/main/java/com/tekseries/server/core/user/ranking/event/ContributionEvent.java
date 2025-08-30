package com.tekseries.server.core.user.ranking.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ContributionEvent extends ApplicationEvent {
    private final String userId;
    private final String type;
    private final String relatedId;

    public ContributionEvent(Object source, String userId, String type, String relatedId) {
        super(source);
        this.userId = userId;
        this.type = type;
        this.relatedId = relatedId;
    }
}
