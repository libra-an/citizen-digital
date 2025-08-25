package com.tekseries.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "user_setting", schema = "demo1")
public class UserSetting {
    @Id
    @Size(max = 36)
    @Column(name = "setting_id", nullable = false, length = 36)
    private String settingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Size(max = 20)
    @ColumnDefault("'light'")
    @Column(name = "theme", length = 20)
    private String theme;

    @Column(name = "notification_preferences")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> notificationPreferences;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

}