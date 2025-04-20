package com.pdn.pdn_api_server.service.implement;


import com.pdn.pdn_api_server.dto.request.QuestionRequest;
import com.pdn.pdn_api_server.dto.response.AnswerResponse;
import com.pdn.pdn_api_server.dto.response.AnswerStatResponse;
import com.pdn.pdn_api_server.dto.response.QuestionResponse;
import com.pdn.pdn_api_server.dto.response.QuestionStatResponse;
import com.pdn.pdn_api_server.entity.Answer;
import com.pdn.pdn_api_server.entity.Class;
import com.pdn.pdn_api_server.entity.Question;
import com.pdn.pdn_api_server.enums.QuestionStatus;
import com.pdn.pdn_api_server.repository.AnswerRepository;
import com.pdn.pdn_api_server.repository.ClassRepository;
import com.pdn.pdn_api_server.repository.QuestionRepository;
import com.pdn.pdn_api_server.service.interfaces.IQuestionService;
import com.pdn.pdn_api_server.service.interfaces.ISseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;
    private final ClassRepository classRepository;
    private final AnswerRepository answerRepository;
    private final ISseService sseService;
    @Override
    @Transactional
    public QuestionResponse createQuestion(QuestionRequest req) {
        Class room = classRepository.findByClassName(req.getClassName())
                .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        Question question = Question.builder()
                .problem(req.getProblem())
                .status(QuestionStatus.CREATED)
                .classRoom(room)
                .build();

        Question saved = questionRepository.save(question);

        List<Answer> answers = req.getAnswerOptions().stream()
                .map(opt -> {
                    Answer a = Answer.builder()
                            .content(opt.getAnswerText())
                            .correct(opt.getIsAnswer())
                            .count(0)
                            .question(saved)
                            .build();
                    System.out.println("✅ 빌더 결과: " + a.getContent() + ", correct=" + a.getCorrect());
                    return a;
                })
                .toList();

        answerRepository.saveAll(answers);
        return QuestionResponse.fromEntity(saved);
    }

    @Override
    public void startQuestion(String className) {
        List<Question> questions = questionRepository.findByClassRoom_ClassName(className);
        for (Question q : questions) {
            q.changeStatus(QuestionStatus.PROGRESS);
        }
        questionRepository.saveAll(questions);
        sseService.broadcast(className, Map.of(
                "status", "STARTED",
                "questions", questions.stream().map(QuestionResponse::fromEntity).toList()
        ));
    }

    @Override
    public void stopQuestion(String className) {
        List<Question> questions = questionRepository.findByClassRoom_ClassName(className);
        for (Question q : questions) {
            q.changeStatus(QuestionStatus.FINISHED);
        }
        questionRepository.saveAll(questions);

        sseService.broadcast(className, Map.of(
                "status", "FINISHED"
        ));
    }

    @Override
    public List<QuestionResponse> getQuestions(String className) {
        return questionRepository.findByClassRoom_ClassName(className).stream()
                .map(QuestionResponse::fromEntity)
                .toList();
    }

    @Override
    public List<AnswerResponse> getAnswers(Long questionId) {
        return answerRepository.findByQuestion_Id(questionId).stream()
                .map(AnswerResponse::fromEntity)
                .toList();
    }

    @Override
    public List<QuestionStatResponse> getQuestionStats(String className) {
        List<Question> questions = questionRepository.findByClassRoom_ClassName(className);

        return questions.stream().map(q -> {
            List<Answer> options = q.getAnswers();
            int total = options.stream().mapToInt(Answer::getCount).sum();

            List<AnswerStatResponse> stats = options.stream()
                    .map(opt -> new AnswerStatResponse(
                            opt.getContent(),
                            opt.getCount(),
                            opt.getCorrect(),
                            total > 0 ? (double) opt.getCount() / total : 0.0
                    ))
                    .toList();

            return new QuestionStatResponse(q.getId(), q.getProblem(), q.getStatus(), stats);
        }).toList();
    }
}
