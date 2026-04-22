package com.example.schedule1app.schedule.dto;

import com.example.schedule1app.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final int commentCount;

    private GetScheduleResponse(Long id, String title, String contents, Long userId,String username, LocalDateTime createdAt, LocalDateTime modifiedAt,int commentCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentCount = commentCount;

    }
    public static GetScheduleResponse from(Schedule schedule, int commentCount){
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                commentCount
        );
    }
}
