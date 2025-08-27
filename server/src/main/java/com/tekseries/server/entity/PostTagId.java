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
public class PostTagId implements Serializable {
    private static final long serialVersionUID = 1802342104999421983L;
    @Size(max = 36)
    @NotNull
    @Column(name = "post_id", nullable = false, length = 36)
    private String postId;

    @Size(max = 36)
    @NotNull
    @Column(name = "tag_id", nullable = false, length = 36)
    private String tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostTagId entity = (PostTagId) o;
        return Objects.equals(this.tagId, entity.tagId) &&
                Objects.equals(this.postId, entity.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, postId);
    }

}