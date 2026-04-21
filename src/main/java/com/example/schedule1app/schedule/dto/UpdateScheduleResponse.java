package com.example.schedule1app.schedule.dto;

import com.example.schedule1app.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final Long userId;
    private final LocalDateTime modifiedAt;

    private UpdateScheduleResponse(Long id, String title, String contents, Long userId, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateScheduleResponse from(Schedule schedule) {
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUser().getId(),
                schedule.getModifiedAt()
        );
    }
}
