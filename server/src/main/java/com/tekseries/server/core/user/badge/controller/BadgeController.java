package com.tekseries.server.core.user.badge.controller;

import com.tekseries.server.core.user.badge.model.UserBadgeDTO;
import com.tekseries.server.core.user.badge.repository.PostRepository;
import com.tekseries.server.core.user.badge.repository.RankingRepository;
import com.tekseries.server.core.user.badge.repository.StreakRepository;
import com.tekseries.server.core.user.badge.service.BadgeService;
import com.tekseries.server.entity.User;
import com.tekseries.server.entity.UserBadge;
import com.tekseries.server.infrastructure.constant.MappingConstants;
import com.tekseries.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(MappingConstants.API_VERSION_PREFIX + MappingConstants.USER + "/{userId}/badges")
public class BadgeController {

    private final BadgeService badgeService;
    private final PostRepository postRepository;
    private final RankingRepository rankingRepository;
    private final StreakRepository streakRepository;
    private final UserRepository userRepository;

    @Autowired
    public BadgeController(BadgeService badgeService,
                           PostRepository postRepository,
                           RankingRepository rankingRepository,
                           StreakRepository streakRepository,
                           UserRepository userRepository) {
        this.badgeService = badgeService;
        this.postRepository = postRepository;
        this.rankingRepository = rankingRepository;
        this.streakRepository = streakRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserBadgeDTO>> getUserBadges(@PathVariable String userId) {

        int postCount = postRepository.countByUserId(userId);
        int totalPoints = userRepository.findById(userId)
                .map(User::getTotalPoints)
                .orElse(0);
        int currentStreak = streakRepository.findCurrentStreakByUser(userId);
        int rankingPosition = rankingRepository.findRankingPositionByUser(userId);

        badgeService.evaluateBadgesForUser(userId, postCount, totalPoints, currentStreak, rankingPosition);

        List<UserBadge> userBadges = badgeService.getBadgesForUser(userId);
        List<UserBadgeDTO> dtos = userBadges.stream()
                .map(ub -> new UserBadgeDTO(
                        ub.getBadge().getId(),
                        ub.getBadge().getName(),
                        ub.getBadge().getDescription(),
                        ub.getBadge().getIconUrl(),
                        ub.getAwardedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}


