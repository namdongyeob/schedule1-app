package com.example.schedule1app.schedule.dto;

import com.example.schedule1app.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetScheduleResponse(Long id, String title, String contents, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static GetScheduleResponse from(Schedule schedule){
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
