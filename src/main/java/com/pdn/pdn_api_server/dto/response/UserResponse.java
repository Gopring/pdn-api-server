package com.pdn.pdn_api_server.dto.response;
import com.pdn.pdn_api_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getId(), user.getName());
    }
}
