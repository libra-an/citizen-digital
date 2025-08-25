package com.tekseries.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "notification", schema = "demo1")
public class Notification {
    @Id
    @Size(max = 36)
    @Column(name = "notification_id", nullable = false, length = 36)
    private String notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Lob
    @Column(name = "content")
    private String content;

    @Size(max = 36)
    @Column(name = "related_id", length = 36)
    private String relatedId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("0")
    @Column(name = "is_read")
    private Boolean isRead;

}