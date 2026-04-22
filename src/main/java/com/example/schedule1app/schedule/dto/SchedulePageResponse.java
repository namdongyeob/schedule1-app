package com.example.schedule1app.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponse {
    private final String title;
    private final String contents;
    private final long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    public SchedulePageResponse(String title, String contents, long commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
    }

}
