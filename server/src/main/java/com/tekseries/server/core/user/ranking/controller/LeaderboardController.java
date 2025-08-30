package com.tekseries.server.core.user.ranking.controller;

import com.tekseries.server.core.admin.dashboardUser.user.repository.AdminUserRepository;
import com.tekseries.server.entity.User;
import com.tekseries.server.infrastructure.constant.MappingConstants;
import com.tekseries.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MappingConstants.API_VERSION_PREFIX + MappingConstants.USER + "/leaderboards")
public class LeaderboardController {

    @Autowired
    private AdminUserRepository userRepository;

    @GetMapping("/top")
    public List<User> getTopUsers(@RequestParam(defaultValue = "10") int limit) {
        return userRepository.findTopNUsers(PageRequest.of(0, limit));
    }
}

