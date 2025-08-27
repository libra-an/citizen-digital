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
public class NoteShareId implements Serializable {
    private static final long serialVersionUID = -4599361879746808073L;
    @Size(max = 36)
    @NotNull
    @Column(name = "note_id", nullable = false, length = 36)
    private String noteId;

    @Size(max = 36)
    @NotNull
    @Column(name = "shared_id", nullable = false, length = 36)
    private String sharedId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteShareId entity = (NoteShareId) o;
        return Objects.equals(this.noteId, entity.noteId) &&
                Objects.equals(this.sharedId, entity.sharedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, sharedId);
    }

}