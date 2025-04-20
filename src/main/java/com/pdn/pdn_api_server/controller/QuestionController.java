package com.pdn.pdn_api_server.controller;

import com.pdn.pdn_api_server.dto.api.ApiResponse;
import com.pdn.pdn_api_server.dto.request.QuestionRequest;
import com.pdn.pdn_api_server.dto.response.AnswerResponse;
import com.pdn.pdn_api_server.dto.response.QuestionResponse;
import com.pdn.pdn_api_server.dto.response.QuestionStatResponse;
import com.pdn.pdn_api_server.service.interfaces.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    // 퀴즈 만들기 (직접)
    @PostMapping
    public ResponseEntity<ApiResponse<QuestionResponse>> createQuestion(@RequestBody QuestionRequest req) {
        QuestionResponse question = questionService.createQuestion(req);
        return ResponseEntity.ok(ApiResponse.success("Question created", question));
    }

    // 퀴즈 시작하기
    @PostMapping("/{className}/start")
    public ResponseEntity<ApiResponse<?>> startQuestion(@PathVariable String className) {
        questionService.startQuestion(className);
        return ResponseEntity.ok(ApiResponse.success("Question started", null));
    }

    // 퀴즈 멈추기
    @PostMapping("/{className}/stop")
    public ResponseEntity<ApiResponse<?>> stopQuestion(@PathVariable String className) {
        questionService.stopQuestion(className);
        return ResponseEntity.ok(ApiResponse.success("Question stopped", null));
    }

    // 퀴즈 리스트 불러오기
    @GetMapping("/class/{className}")
    public ResponseEntity<ApiResponse<List<QuestionResponse>>> getQuestions(@PathVariable String className) {
        List<QuestionResponse> questions = questionService.getQuestions(className);
        return ResponseEntity.ok(ApiResponse.success("Questions fetched", questions));
    }

    // 정답 불러오기
    @GetMapping("/{id}/answers")
    public ResponseEntity<ApiResponse<List<AnswerResponse>>> getAnswers(@PathVariable Long id) {
        List<AnswerResponse> answers = questionService.getAnswers(id);
        return ResponseEntity.ok(ApiResponse.success("Answer status fetched", answers));
    }

    // 퀴즈 통계 불러오기
    @GetMapping("/class/{className}/stats")
    public ResponseEntity<ApiResponse<List<QuestionStatResponse>>> getQuestionStats(@PathVariable String className) {
        List<QuestionStatResponse> stats = questionService.getQuestionStats(className);
        return ResponseEntity.ok(ApiResponse.success("Quiz stats fetched", stats));
    }

}
