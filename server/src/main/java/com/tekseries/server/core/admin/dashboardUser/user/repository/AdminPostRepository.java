package com.tekseries.server.core.admin.dashboardUser.user.repository;

import com.tekseries.server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPostRepository extends JpaRepository<Post, String> {
}
