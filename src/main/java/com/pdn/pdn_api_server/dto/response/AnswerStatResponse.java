package com.pdn.pdn_api_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerStatResponse {
    private String answerText;
    private int count;
    private Boolean isAnswer;
    private double rate;
}
