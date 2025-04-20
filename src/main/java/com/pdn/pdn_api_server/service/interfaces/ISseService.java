package com.pdn.pdn_api_server.service.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public interface ISseService {
    SseEmitter subscribe(String className);
    void broadcast(String className, Object data);
}
