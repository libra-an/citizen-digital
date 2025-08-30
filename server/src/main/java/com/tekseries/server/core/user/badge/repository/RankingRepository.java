package com.tekseries.server.core.user.badge.repository;

import com.tekseries.server.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, String> {

    @Query("SELECT r.rankPosition FROM Ranking r WHERE r.user.id = :userId AND r.rankingType = 'all_time'")
    Integer findRankingPositionByUser(@Param("userId") String userId);
}

