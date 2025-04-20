package com.pdn.pdn_api_server.service.interfaces;

import com.pdn.pdn_api_server.dto.request.QuestionRequest;
import com.pdn.pdn_api_server.dto.response.AnswerResponse;
import com.pdn.pdn_api_server.dto.response.QuestionResponse;
import com.pdn.pdn_api_server.dto.response.QuestionStatResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IQuestionService {
    QuestionResponse createQuestion(QuestionRequest req);
    void startQuestion(String className);
    void stopQuestion(String className);
    List<QuestionResponse> getQuestions(String className);
    List<AnswerResponse> getAnswers(Long questionId);
    List<QuestionStatResponse> getQuestionStats(String className);
}
