package com.tekseries.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "question_post", schema = "demo1")
public class QuestionPost {
    @Id
    @Size(max = 36)
    @Column(name = "question_id", nullable = false, length = 36)
    private String questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    private Post post;

    @Lob
    @Column(name = "code_snippet")
    private String codeSnippet;

    @Size(max = 20)
    @ColumnDefault("'open'")
    @Column(name = "status", length = 20)
    private String status;

}