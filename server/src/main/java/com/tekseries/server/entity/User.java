package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
import com.tekseries.server.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "citizen_digital")
//@AttributeOverride(
//        name = "id",
//        column = @Column(name = "user_id", length = EntityProperties.LENGTH_ID, updatable = false, nullable = false)
//)
public class User extends PrimaryEntity implements Serializable {

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 255)
    @Column(name = "profile_picture")
    private String profilePicture;

    @Size(max = 100)
    @Column(name = "major", length = 100)
    private String major;

    @Size(max = 50)
    @Column(name = "class", length = 50)
    private String classField;

    @Lob
    @Column(name = "bio")
    private String bio;

    @Size(max = 50)
    @ColumnDefault("'USER'")
    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column
    private Long banExpiryDate;

    @Column(name = "total_points")
    private Integer totalPoints = 0;


    public User(String id) {
        this.setId(id);
    }
}