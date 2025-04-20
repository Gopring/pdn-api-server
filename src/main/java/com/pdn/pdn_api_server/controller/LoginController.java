package com.pdn.pdn_api_server.controller;

import com.pdn.pdn_api_server.dto.request.ProfessorLoginRequest;
import com.pdn.pdn_api_server.dto.request.StudentLoginRequest;
import com.pdn.pdn_api_server.dto.api.ApiResponse;
import com.pdn.pdn_api_server.entity.Class;
import com.pdn.pdn_api_server.service.implement.ClassService;
import com.pdn.pdn_api_server.service.implement.UserService;
import com.pdn.pdn_api_server.service.interfaces.IClassService;
import com.pdn.pdn_api_server.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final IUserService userService;
    private final IClassService classService;

    // 교수님 방 입장 (class 없을 시 생성)
    @PostMapping("/professors")
    public ResponseEntity<ApiResponse<?>> loginProfessor(@RequestBody ProfessorLoginRequest req) {
        Class room = classService.createOrValidateClass(req);
        return ResponseEntity.ok(ApiResponse.success("Professor login successful",
                Map.of("status", "professor", "className", room.getClassName())));
    }

    // 학생 방 입장
    @PostMapping("/students")
    public ResponseEntity<ApiResponse<?>> loginStudent(@RequestBody StudentLoginRequest req) {
        userService.registerStudent(req);
        return ResponseEntity.ok(ApiResponse.success("Student login successful",
                Map.of("status", "student", "className", req.getClassName())));
    }

}
