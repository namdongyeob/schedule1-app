package com.example.schedule1app.schedule.entity;

import com.example.schedule1app.gobal.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String authorName;

    public Schedule(String title, String contents, String authorName) {
        this.title = title;
        this.contents = contents;
        this.authorName = authorName;

    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
