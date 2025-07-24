package com.tekseries.server.infrastructure.listener;

import com.tekseries.server.entity.base.PrimaryEntity;
import com.tekseries.server.infrastructure.constant.EntityStatus;
import jakarta.persistence.PrePersist;


import java.util.UUID;

public class CreatePrimaryEntityListener {

    @PrePersist
    private void onCreate(PrimaryEntity entity) {
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(EntityStatus.ACTIVE);
    }

}
