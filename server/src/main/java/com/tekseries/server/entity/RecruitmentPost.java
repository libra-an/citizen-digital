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
@Table(name = "recruitment_post", schema = "demo1")
public class RecruitmentPost {
    @Id
    @Size(max = 36)
    @Column(name = "recruitment_id", nullable = false, length = 36)
    private String recruitmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    private Post post;

    @Lob
    @Column(name = "requirements")
    private String requirements;

    @Lob
    @Column(name = "contact_info")
    private String contactInfo;

}