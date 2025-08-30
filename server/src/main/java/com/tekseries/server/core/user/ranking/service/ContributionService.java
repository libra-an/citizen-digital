package com.tekseries.server.core.user.ranking.service;

import com.tekseries.server.core.user.ranking.event.ContributionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ContributionService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void addContribution(String userId, String type, String relatedId) {
        ContributionEvent event = new ContributionEvent(this, userId, type, relatedId);
        publisher.publishEvent(event);
    }
}

