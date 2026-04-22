package com.example.schedule1app.gobal.excption;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ServiceException {
    public ScheduleNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 일정을 찾을 수 없습니다.");
    }
}
