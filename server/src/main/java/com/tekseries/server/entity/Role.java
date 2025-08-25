package com.tekseries.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role", schema = "demo1")
public class Role {
    @Id
    @Size(max = 36)
    @Column(name = "role_id", nullable = false, length = 36)
    private String roleId;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

}