package com.pdn.pdn_api_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {
    private String studentId;
    private Long questionId;
    private String selectedAnswer;
}
