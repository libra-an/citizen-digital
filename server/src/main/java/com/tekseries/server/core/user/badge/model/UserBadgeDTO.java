package com.tekseries.server.core.user.badge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBadgeDTO {
    private String badgeId;
    private String name;
    private String description;
    private String iconUrl;
    private Instant awardedAt;
}

