package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
import com.tekseries.server.infrastructure.constant.EntityProperties;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification", schema = "citizen_digital")
@AttributeOverride(
        name = "id",
        column = @Column(name = "notification_id", length = EntityProperties.LENGTH_ID, updatable = false, nullable = false)
)
public class Notification extends PrimaryEntity implements Serializable {


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

    @ColumnDefault("0")
    @Column(name = "is_read")
    private Boolean isRead;

}