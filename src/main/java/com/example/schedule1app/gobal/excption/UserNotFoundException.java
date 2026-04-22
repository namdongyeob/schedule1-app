package com.example.schedule1app.gobal.excption;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다.");
    }

}
