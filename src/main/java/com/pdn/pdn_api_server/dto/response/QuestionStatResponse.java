package com.pdn.pdn_api_server.dto.response;

import com.pdn.pdn_api_server.enums.QuestionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionStatResponse {
    private Long id;
    private String problem;
    private QuestionStatus status;
    private List<AnswerStatResponse> answerOptions;
}
