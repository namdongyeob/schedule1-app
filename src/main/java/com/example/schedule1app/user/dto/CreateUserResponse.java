package com.example.schedule1app.user.dto;

import com.example.schedule1app.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;


    private CreateUserResponse(Long id, String username, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
