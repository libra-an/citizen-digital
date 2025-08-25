package com.tekseries.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EventTagId implements Serializable {
    private static final long serialVersionUID = 8196963618033226400L;
    @Size(max = 36)
    @NotNull
    @Column(name = "event_id", nullable = false, length = 36)
    private String eventId;

    @Size(max = 36)
    @NotNull
    @Column(name = "tag_id", nullable = false, length = 36)
    private String tagId;

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