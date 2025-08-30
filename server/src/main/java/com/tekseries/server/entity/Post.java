package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
import com.tekseries.server.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "citizen_digital")
public class Post extends PrimaryEntity implements Serializable {


    @Lob
    @Column(name = "content")
    private String content;

    @Size(max = 50)
    @ColumnDefault("'public'")
    @Column(name = "visibility", length = 50)
    private String visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}