package com.tekseries.server.entity.base;

import com.tekseries.server.infrastructure.constant.EntityProperties;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import com.tekseries.server.infrastructure.listener.CreatePrimaryEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(CreatePrimaryEntityListener.class)
public abstract class PrimaryEntity extends AuditEntity implements IsIdentified {

    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;

}
