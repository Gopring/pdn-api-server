package com.pdn.pdn_api_server.service.implement;

import com.pdn.pdn_api_server.dto.request.ProfessorLoginRequest;
import com.pdn.pdn_api_server.entity.Class;
import com.pdn.pdn_api_server.repository.ClassRepository;
import com.pdn.pdn_api_server.service.interfaces.IClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClassService implements IClassService {
    private final ClassRepository classRepository;
    @Override
    public Class createOrValidateClass(ProfessorLoginRequest req) {
        return classRepository.findByClassName(req.getClassName())
                .map(existing -> {
                    if (!existing.getPassword().equals(req.getPassword())) {
                        throw new IllegalArgumentException("Incorrect password for existing class");
                    }
                    return existing;
                })
                .orElseGet(() -> classRepository.save(Class.builder()
                        .className(req.getClassName())
                        .password(req.getPassword())
                        .professorName(req.getProfessorName())
                        .build()));
    }

    @Override
    @Transactional
    public void deleteClass(String className, String password) {
        Class room = classRepository.findByClassName(className)
                .orElseThrow(() -> new IllegalArgumentException("Class not found"));
        if (!room.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }
        classRepository.deleteByClassName(className);
    }
}
