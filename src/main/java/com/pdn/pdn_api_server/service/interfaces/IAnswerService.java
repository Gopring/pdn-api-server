package com.pdn.pdn_api_server.service.interfaces;

import com.pdn.pdn_api_server.dto.request.AnswerRequest;
import org.springframework.stereotype.Service;

@Service
public interface IAnswerService {
    void submitAnswer(AnswerRequest req);
}
