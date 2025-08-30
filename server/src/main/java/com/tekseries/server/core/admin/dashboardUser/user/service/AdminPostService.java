package com.tekseries.server.core.admin.dashboardUser.user.service;

public interface AdminPostService {
    boolean deletePost(String postId);

    boolean softDeletePost(String postId);
}
