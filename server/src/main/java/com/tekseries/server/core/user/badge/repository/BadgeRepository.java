package com.tekseries.server.core.user.badge.repository;

import com.tekseries.server.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, String> {
    List<Badge> findAll();
}

