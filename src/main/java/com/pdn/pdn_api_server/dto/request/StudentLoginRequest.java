package com.pdn.pdn_api_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentLoginRequest {
    private String id;
    private String username;
    private String className;
}
