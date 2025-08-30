package com.tekseries.server.core.admin.dashboardUser.user.repository;

import com.tekseries.server.entity.User;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminUserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    List<User> findByStatus(EntityStatus status);

    @Query("SELECT u FROM User u WHERE u.status <> :status")
    List<User> findUsersNotDeleted(@Param("status") EntityStatus status);

    @Query("SELECT u FROM User u ORDER BY u.totalPoints DESC")
    List<User> findTopNUsers(Pageable pageable);
}
