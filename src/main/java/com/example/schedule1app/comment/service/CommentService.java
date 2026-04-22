package com.example.schedule1app.comment.service;

import com.example.schedule1app.comment.dto.CreateCommentRequest;
import com.example.schedule1app.comment.dto.CreateCommentResponse;
import com.example.schedule1app.comment.dto.GetCommentResponse;
import com.example.schedule1app.comment.entity.Comment;
import com.example.schedule1app.comment.repository.CommentRepository;
import com.example.schedule1app.schedule.entity.Schedule;
import com.example.schedule1app.schedule.repository.ScheduleRepository;
import com.example.schedule1app.user.entity.User;
import com.example.schedule1app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다."));
        Comment comment = new Comment(request.getContents(), user, schedule);
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponse.from(savedComment);
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> findAll(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId)
                .stream().map(GetCommentResponse::from)
                .toList();
    }
}

