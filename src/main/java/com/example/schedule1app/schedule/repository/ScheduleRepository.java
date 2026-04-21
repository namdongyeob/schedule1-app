package com.example.schedule1app.schedule.repository;

import com.example.schedule1app.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
