package com.pdn.pdn_api_server.entity;

import com.pdn.pdn_api_server.enums.QuestionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String problem;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status; // "created, "progress", "finished"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classRoom;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public void changeStatus(QuestionStatus nextStatus) {
        if (!this.status.canTransitionTo(nextStatus)) {
            throw new IllegalStateException("Invalid status transition: " + this.status + " → " + nextStatus);
        }
        this.status = nextStatus;
    }
}
