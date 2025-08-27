package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "streak", schema = "demo1")
public class Streak extends PrimaryEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user2_id")
    private User user2;

    @ColumnDefault("0")
    @Column(name = "current_streak")
    private Integer currentStreak;

    @Column(name = "last_interaction_date")
    private LocalDate lastInteractionDate;

    @Column(name = "last_week_number")
    private Integer lastWeekNumber;

    @ColumnDefault("0")
    @Column(name = "restore_count")
    private Integer restoreCount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

}