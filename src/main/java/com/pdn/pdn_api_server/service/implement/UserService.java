package com.pdn.pdn_api_server.service.implement;

import com.pdn.pdn_api_server.dto.request.StudentLoginRequest;
import com.pdn.pdn_api_server.dto.response.UserResponse;
import com.pdn.pdn_api_server.entity.Class;
import com.pdn.pdn_api_server.entity.User;
import com.pdn.pdn_api_server.repository.ClassRepository;
import com.pdn.pdn_api_server.repository.UserRepository;
import com.pdn.pdn_api_server.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    @Override
    public void registerStudent(StudentLoginRequest req) {
        Class room = classRepository.findByClassName(req.getClassName())
                .orElseThrow(() -> new IllegalArgumentException("Class not found"));
        User user = User.builder()
                .id(req.getId())
                .name(req.getUsername())
                .classRoom(room)
                .build();
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getStudents(String className) {
        return userRepository.findByClassRoom_ClassName(className).stream()
                .map(UserResponse::fromEntity)
                .toList();
    }
}
