package com.tekseries.server.core.user.badge.service;

import com.tekseries.server.entity.Badge;
import com.tekseries.server.entity.UserBadge;

import java.util.List;

public interface BadgeService {

    List<UserBadge> getBadgesForUser(String userId);

    void awardBadge(String userId, Badge badge);

    void evaluateBadgesForUser(String userId, int postCount, int totalPoints, int currentStreak, int rankingPosition);
}
