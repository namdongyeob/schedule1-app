package com.example.schedule1app.comment.controller;

import com.example.schedule1app.comment.dto.CreateCommentRequest;
import com.example.schedule1app.comment.dto.CreateCommentResponse;
import com.example.schedule1app.comment.dto.GetCommentResponse;
import com.example.schedule1app.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(
            @RequestBody CreateCommentRequest request, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request,userId));
    }

    @GetMapping
    public ResponseEntity<List<GetCommentResponse>> getCommentAll(
            @RequestParam Long scheduleId) {  // ← scheduleId 추가
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll(scheduleId));
    }
}

