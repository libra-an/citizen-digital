package com.tekseries.server.core.user.badge.repository;

import com.tekseries.server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId AND p.status = 'ACTIVE'")
    int countByUserId(@Param("userId") String userId);
}

