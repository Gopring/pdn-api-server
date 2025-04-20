package com.pdn.pdn_api_server.service.interfaces;

import com.pdn.pdn_api_server.dto.request.StudentLoginRequest;
import com.pdn.pdn_api_server.dto.response.UserResponse;
import com.pdn.pdn_api_server.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    void registerStudent(StudentLoginRequest req);
    List<UserResponse> getStudents(String className);
}
