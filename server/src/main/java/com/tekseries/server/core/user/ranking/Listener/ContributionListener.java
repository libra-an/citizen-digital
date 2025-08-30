package com.tekseries.server.core.user.ranking.Listener;

import com.tekseries.server.core.admin.dashboardUser.user.repository.AdminUserRepository;
import com.tekseries.server.core.user.ranking.event.ContributionEvent;
import com.tekseries.server.core.user.ranking.repository.ContributionRepository;
import com.tekseries.server.entity.Contribution;
import com.tekseries.server.entity.User;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ContributionListener {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private AdminUserRepository userRepository;

    @EventListener
    @Transactional
    public void handleContributionEvent(ContributionEvent event) {
        int points = calculatePoints(event.getType());

        User user = userRepository.findById(event.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contribution contribution = new Contribution();
        contribution.setId(UUID.randomUUID().toString());
        contribution.setUser(user);
        contribution.setType(event.getType());
        contribution.setPoints(points);
        contribution.setRelatedId(event.getRelatedId());
        contribution.setCreatedAt(Instant.now());
        contribution.setStatus(EntityStatus.ACTIVE);

        contributionRepository.save(contribution);


        user.setTotalPoints((user.getTotalPoints() == null ? 0 : user.getTotalPoints()) + points);
        userRepository.save(user);
    }

    private int calculatePoints(String type) {
        return switch (type) {
            case "CREATE_POST" -> 10;
            case "COMMENT" -> 5;
            case "LIKE" -> 1;
            case "ANSWER" -> 5;
            default -> 0;
        };
    }
}

