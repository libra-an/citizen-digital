package com.tekseries.server.entity;

import com.tekseries.server.entity.base.PrimaryEntity;
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
@Table(name = "message", schema = "citizen_digital")
public class Message extends PrimaryEntity implements Serializable {


    @Lob
    @Column(name = "content")
    private String content;

    @Size(max = 255)
    @Column(name = "file_url")
    private String fileUrl;

    @ColumnDefault("0")
    @Column(name = "is_reply")
    private Boolean isReply;

}