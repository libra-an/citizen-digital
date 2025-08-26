package com.tekseries.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EventTagId implements Serializable {
    private static final long serialVersionUID = 8196963618033226400L;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @NotNull
    @Column(name = "tag_id", nullable = false)
    private UUID tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventTagId entity = (EventTagId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.tagId, entity.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, tagId);
    }
}