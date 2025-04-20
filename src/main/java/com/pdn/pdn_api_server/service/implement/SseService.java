package com.pdn.pdn_api_server.service.implement;

import com.pdn.pdn_api_server.service.interfaces.ISseService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService implements ISseService {
    private final Map<String, List<SseEmitter>> emitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter subscribe(String className) {
        SseEmitter emitter = new SseEmitter(60 * 60 * 1000L);
        emitters.computeIfAbsent(className, key -> new ArrayList<>()).add(emitter);

        emitter.onCompletion(() -> emitters.get(className).remove(emitter));
        emitter.onTimeout(() -> emitters.get(className).remove(emitter));

        return emitter;
    }

    @Override
    public void broadcast(String className, Object data) {
        List<SseEmitter> list = emitters.getOrDefault(className, new ArrayList<>());
        for (SseEmitter emitter : list) {
            try {
                emitter.send(SseEmitter.event().name("quiz").data(data));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }
    }
}
