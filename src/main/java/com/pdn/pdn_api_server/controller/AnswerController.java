package com.pdn.pdn_api_server.controller;

import com.pdn.pdn_api_server.dto.api.ApiResponse;
import com.pdn.pdn_api_server.dto.request.AnswerRequest;
import com.pdn.pdn_api_server.service.interfaces.IAnswerService;
import com.pdn.pdn_api_server.service.interfaces.ISseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final IAnswerService answerService;
    private final ISseService sseService;

    @PostMapping("/answers")
    public ResponseEntity<ApiResponse<?>> submitAnswer(@RequestBody AnswerRequest req) {
        answerService.submitAnswer(req);
        return ResponseEntity.ok(ApiResponse.success("Answer submitted", null));
    }

    @GetMapping(value = "/subscribe/class/{className}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String className) {
        return sseService.subscribe(className);
    }
}
