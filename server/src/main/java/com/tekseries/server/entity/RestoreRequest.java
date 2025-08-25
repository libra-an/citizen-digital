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
@Table(name = "restore_request", schema = "demo1")
public class RestoreRequest {
    @Id
    @Size(max = 36)
    @Column(name = "request_id", nullable = false, length = 36)
    private String requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "streak_id")
    private Streak streak;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "requester_id")
    private User requester;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "request_date")
    private Instant requestDate;

    @Size(max = 10)
    @ColumnDefault("'pending'")
    @Column(name = "status", length = 10)
    private String status;

    @Lob
    @Column(name = "reason")
    private String reason;

}