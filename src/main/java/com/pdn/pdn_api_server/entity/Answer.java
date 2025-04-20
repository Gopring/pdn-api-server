package com.pdn.pdn_api_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 문항

    private Integer count = 0; // 선택한 학생 수

    private Boolean correct; // 정답 여부

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public void incrementCount() {
        this.count += 1;
    }
}
