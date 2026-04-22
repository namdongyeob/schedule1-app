package com.example.schedule1app.gobal.excption;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends ServiceException {
    public CommentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다.");
    }
}
