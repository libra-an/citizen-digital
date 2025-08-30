package com.tekseries.server.core.user.badge.repository;


import com.tekseries.server.entity.Streak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StreakRepository extends JpaRepository<Streak, String> {
    @Query("SELECT COALESCE(SUM(s.currentStreak), 0) FROM Streak s " +
            "WHERE s.user1.id = :userId OR s.user2.id = :userId")
    int findCurrentStreakByUser(@Param("userId") String userId);
}

