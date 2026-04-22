package com.example.schedule1app.schedule.service;

import com.example.schedule1app.comment.repository.CommentRepository;
import com.example.schedule1app.gobal.excption.ScheduleNotFoundException;
import com.example.schedule1app.gobal.excption.UserNotFoundException;
import com.example.schedule1app.schedule.dto.*;
import com.example.schedule1app.schedule.entity.Schedule;
import com.example.schedule1app.schedule.repository.ScheduleRepository;
import com.example.schedule1app.user.entity.User;
import com.example.schedule1app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponse create(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                UserNotFoundException::new);
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return CreateScheduleResponse.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> GetScheduleResponse.from(  // :: 대신 . 사용!
                        schedule,
                        commentRepository.countByScheduleId(schedule.getId())
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new);
        int commentCount = commentRepository.countByScheduleId(scheduleId);
        return GetScheduleResponse.from(schedule, commentCount);
    }

    @Transactional
    public UpdateScheduleResponse update(UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new);
        schedule.update(request.getTitle(), request.getContents());
        return UpdateScheduleResponse.from(schedule);
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new);
        scheduleRepository.delete(schedule);
    }
    @Transactional(readOnly = true)
    public Page<SchedulePageResponse> getAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return scheduleRepository.findAllWithCommentCount(pageable);
    }
}