package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "streak_history", schema = "demo1")
public class StreakHistory extends PrimaryEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "streak_id")
    private Streak streak;

    @Column(name = "previous_streak")
    private Integer previousStreak;

    @Size(max = 20)
    @Column(name = "action_type", length = 20)
    private String actionType;

    @Column(name = "action_date")
    private LocalDate actionDate;

    @Lob
    @Column(name = "reason")
    private String reason;

}