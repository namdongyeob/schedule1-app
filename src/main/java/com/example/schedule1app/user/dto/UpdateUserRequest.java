package com.example.schedule1app.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String username;
    private String email;
    @Size(min = 8, message = "비밀번호는 8글자 이상이여야 합니다.")
    private String password;
}
