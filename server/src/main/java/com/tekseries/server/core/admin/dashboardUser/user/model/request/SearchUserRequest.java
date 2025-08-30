package com.tekseries.server.core.admin.dashboardUser.user.model.request;

import lombok.Data;

@Data
public class SearchUserRequest {
    private String keyword;
    private String role;
}
