package com.pdn.pdn_api_server.service.implement;

import com.pdn.pdn_api_server.dto.request.AnswerRequest;
import com.pdn.pdn_api_server.entity.Answer;
import com.pdn.pdn_api_server.entity.Question;
import com.pdn.pdn_api_server.repository.AnswerRepository;
import com.pdn.pdn_api_server.repository.QuestionRepository;
import com.pdn.pdn_api_server.service.interfaces.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @Override
    public void submitAnswer(AnswerRequest req) {
        Question question = questionRepository.findById(req.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        Answer selected = question.getAnswers().stream()
                .filter(a -> a.getContent().equals(req.getSelectedAnswer()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Selected answer not found"));

        selected.incrementCount();

        answerRepository.save(selected);
    }
}

