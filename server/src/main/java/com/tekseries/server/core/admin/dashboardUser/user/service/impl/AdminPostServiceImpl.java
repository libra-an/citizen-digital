package com.tekseries.server.core.admin.dashboardUser.user.service.impl;

import com.tekseries.server.core.admin.dashboardUser.user.repository.AdminPostRepository;
import com.tekseries.server.core.admin.dashboardUser.user.service.AdminPostService;
import com.tekseries.server.entity.Post;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminPostServiceImpl implements AdminPostService {

    @Autowired
    private AdminPostRepository adminPostRepository;

    // Hard delete
    @Override
    @Transactional
    public boolean deletePost(String postId) {
        if (!adminPostRepository.existsById(postId)) {
            return false;
        }
        adminPostRepository.deleteById(postId);
        return true;
    }

    // Soft delete
    @Override
    @Transactional
    public boolean softDeletePost(String postId) {
        Optional<Post> optionalPost = adminPostRepository.findById(postId);
        if (!optionalPost.isPresent()) {
            return false;
        }
        Post post = optionalPost.get();
        post.setStatus(EntityStatus.DELETED);
        adminPostRepository.save(post);
        return true;
    }
}
