package com.tekseries.server.core.user.badge.service.impl;

import com.tekseries.server.core.user.badge.repository.BadgeRepository;
import com.tekseries.server.core.user.badge.repository.UserBadgeRepository;
import com.tekseries.server.core.user.badge.service.BadgeService;
import com.tekseries.server.entity.Badge;
import com.tekseries.server.entity.User;
import com.tekseries.server.entity.UserBadge;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BadgeServiceImpl(BadgeRepository badgeRepository,
                            UserBadgeRepository userBadgeRepository,
                            ObjectMapper objectMapper) {
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UserBadge> getBadgesForUser(String userId) {
        return userBadgeRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public void awardBadge(String userId, Badge badge) {
        if (!userBadgeRepository.existsByUserIdAndBadgeId(userId, badge.getId())) {
            UserBadge userBadge = new UserBadge();
            userBadge.setUser(new User(userId));
            userBadge.setBadge(badge);
            userBadge.setAwardedAt(Instant.now());
            userBadgeRepository.save(userBadge);
        }
    }

    @Override
    @Transactional
    public void evaluateBadgesForUser(String userId, int postCount, int totalPoints, int currentStreak, int rankingPosition) {
        List<Badge> allBadges = badgeRepository.findAll();

        for (Badge badge : allBadges) {
            String criteriaJson = badge.getCriteria();
            if (criteriaJson == null || criteriaJson.isEmpty()) continue;

            try {
                JsonNode criteriaNode = objectMapper.readTree(criteriaJson);
                String type = criteriaNode.get("type").asText();
                int value = criteriaNode.get("value").asInt();

                boolean shouldAward = false;

                switch (type) {
                    case "post_count":
                        if (postCount >= value) shouldAward = true;
                        break;
                    case "total_points":
                        if (totalPoints >= value) shouldAward = true;
                        break;
                    case "streak":
                        if (currentStreak >= value) shouldAward = true;
                        break;
                    case "ranking_position":
                        if (rankingPosition <= value) shouldAward = true;
                        break;
                    default:
                        break;
                }

                if (shouldAward) {
                    awardBadge(userId, badge);
                }

            } catch (Exception e) {
                System.err.println("Invalid criteria JSON for badge " + badge.getId() + ": " + e.getMessage());
            }
        }
    }

}
