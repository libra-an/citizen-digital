package com.tekseries.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "note_share", schema = "demo1")
public class NoteShare {
    @EmbeddedId
    private NoteShareId id;

    @MapsId("noteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;

    @Size(max = 20)
    @Column(name = "share_type", length = 20)
    private String shareType;

}