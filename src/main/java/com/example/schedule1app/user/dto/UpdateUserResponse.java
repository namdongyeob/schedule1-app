package com.example.schedule1app.user.dto;

import com.example.schedule1app.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateUserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime modifiedAt;

    private UpdateUserResponse(Long id, String username, String email, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateUserResponse from(User user) {
        return new UpdateUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getModifiedAt()
        );
    }

}
