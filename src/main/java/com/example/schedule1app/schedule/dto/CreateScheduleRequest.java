package com.example.schedule1app.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private Long userId;
}