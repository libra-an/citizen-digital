package com.tekseries.server.core.admin.dashboardUser.user.model.response;

import com.tekseries.server.entity.User;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private String profilePicture;
    private String major;
    private String className;
    private String bio;
    private String role;
    private EntityStatus status;
    private Instant lastLogin;

    public UserResponse() {
    }

    public UserResponse(User u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.fullName = u.getFullName();
        this.profilePicture = u.getProfilePicture();
        this.major = u.getMajor();
        this.className = u.getClassField();
        this.bio = u.getBio();
        this.role = u.getRole();
        this.status = u.getStatus();
        this.lastLogin = u.getLastLogin();
    }
}
