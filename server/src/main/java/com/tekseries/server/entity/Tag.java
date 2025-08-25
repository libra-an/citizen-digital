package com.tekseries.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tag", schema = "demo1")
public class Tag {
    @Id
    @Size(max = 36)
    @Column(name = "tag_id", nullable = false, length = 36)
    private String tagId;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

}