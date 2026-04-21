package com.example.schedule1app.schedule.entity;

import com.example.schedule1app.gobal.entity.BaseEntity;
import com.example.schedule1app.user.entity.User;
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
    @ManyToOne(fetch = FetchType.LAZY) // 여러 일정은 한 명의 유저에게 속합니다.
    @JoinColumn(name = "user_id") // DB 테이블의 외래 키(FK) 컬럼명을 설정합니다.
    private User user;

    public Schedule(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;

    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
