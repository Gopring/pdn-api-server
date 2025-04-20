package com.pdn.pdn_api_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String className;
    private String problem;
    private List<AnswerOptionDto> answerOptions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerOptionDto {
        private String answerText;
        private Boolean isAnswer;
    }
}
