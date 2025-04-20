package com.pdn.pdn_api_server.dto.response;

import com.pdn.pdn_api_server.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResponse {
    private String answerText;
    private int count;
    private Boolean isAnswer;

    public static AnswerResponse fromEntity(Answer option) {
        return new AnswerResponse(
                option.getContent(),
                option.getCount(),
                option.getCorrect()
        );
    }
}
