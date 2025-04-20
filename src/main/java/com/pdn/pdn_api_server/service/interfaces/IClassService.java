package com.pdn.pdn_api_server.service.interfaces;

import com.pdn.pdn_api_server.dto.request.ProfessorLoginRequest;
import com.pdn.pdn_api_server.entity.Class;
import org.springframework.stereotype.Service;

@Service
public interface IClassService {
    Class createOrValidateClass(ProfessorLoginRequest req);
    void deleteClass(String className, String password);
}
