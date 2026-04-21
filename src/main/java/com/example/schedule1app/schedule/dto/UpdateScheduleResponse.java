package com.example.schedule1app.schedule.dto;

import com.example.schedule1app.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private UpdateScheduleResponse(Long id, String title, String contents, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateScheduleResponse from(Schedule schedule) {
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
