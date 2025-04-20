package com.pdn.pdn_api_server.dto.response;

import com.pdn.pdn_api_server.entity.Question;
import com.pdn.pdn_api_server.enums.QuestionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String problem;
    private QuestionStatus status;

    public static QuestionResponse fromEntity(Question question) {
        return new QuestionResponse(
                question.getId(),
                question.getProblem(),
                question.getStatus()
        );
    }
}
