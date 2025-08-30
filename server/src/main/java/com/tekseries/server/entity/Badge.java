package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "badge", schema = "citizen_digital")
public class Badge extends PrimaryEntity implements Serializable {

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "criteria", columnDefinition = "JSON")
    private String criteria;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}