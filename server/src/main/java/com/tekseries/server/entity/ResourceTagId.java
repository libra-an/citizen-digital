package com.tekseries.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ResourceTagId implements Serializable {
    private static final long serialVersionUID = -5467015467558420923L;
    @Size(max = 36)
    @NotNull
    @Column(name = "resource_id", nullable = false, length = 36)
    private String resourceId;

    @Size(max = 36)
    @NotNull
    @Column(name = "tag_id", nullable = false, length = 36)
    private String tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResourceTagId entity = (ResourceTagId) o;
        return Objects.equals(this.resourceId, entity.resourceId) &&
                Objects.equals(this.tagId, entity.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, tagId);
    }

}