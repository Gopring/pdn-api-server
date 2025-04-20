package com.pdn.pdn_api_server.controller;

import com.pdn.pdn_api_server.dto.api.ApiResponse;
import com.pdn.pdn_api_server.dto.request.ClassDeleteRequest;
import com.pdn.pdn_api_server.dto.response.UserResponse;
import com.pdn.pdn_api_server.service.implement.ClassService;
import com.pdn.pdn_api_server.service.implement.UserService;
import com.pdn.pdn_api_server.service.interfaces.IClassService;
import com.pdn.pdn_api_server.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {

    private final IClassService classService;
    private final IUserService userService;

    // 방 삭제 (className, password 일치 시)
    @DeleteMapping("/{className}")
    public ResponseEntity<ApiResponse<?>> deleteClass(@PathVariable String className, @RequestBody ClassDeleteRequest req) {
        classService.deleteClass(className, req.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Class deleted", null));
    }

    // 해당 class의 학생 리스트
    @GetMapping("/{className}")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getStudents(@PathVariable String className) {
        List<UserResponse> students = userService.getStudents(className);
        return ResponseEntity.ok(ApiResponse.success("Student list fetched", students));
    }

}
