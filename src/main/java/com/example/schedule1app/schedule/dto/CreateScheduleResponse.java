package com.example.schedule1app.schedule.dto;

import com.example.schedule1app.schedule.entity.Schedule;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String authorName;
    private final LocalDateTime createdAt;   // ← 추가
    private final LocalDateTime modifiedAt;


    private CreateScheduleResponse(Long id, String title, String contents,String authorname,LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.authorName = authorname;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }
    public static CreateScheduleResponse from(Schedule schedule){
        return new CreateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
