package com.example.schedule1app.schedule.service;

import com.example.schedule1app.schedule.dto.*;
import com.example.schedule1app.schedule.entity.Schedule;
import com.example.schedule1app.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse create(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getAuthorName());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return CreateScheduleResponse.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(GetScheduleResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않은 일정입니다."));
        return GetScheduleResponse.from(schedule);
    }

    @Transactional
    public UpdateScheduleResponse update(UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        schedule.update(request.getTitle(), request.getAuthorName());
        return UpdateScheduleResponse.from(schedule);
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하는 일정이 없습니다."));
        scheduleRepository.delete(schedule);
    }
}