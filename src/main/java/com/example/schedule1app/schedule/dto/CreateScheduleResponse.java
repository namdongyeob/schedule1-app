package com.example.schedule1app.schedule.dto;

import com.example.schedule1app.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final Long userId;
    private final LocalDateTime createdAt;


    private CreateScheduleResponse(Long id, String title, String contents, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.createdAt = createdAt;


    }

    public static CreateScheduleResponse from(Schedule schedule) {
        return new CreateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUser().getId(),
                schedule.getCreatedAt()
        );
    }
}
