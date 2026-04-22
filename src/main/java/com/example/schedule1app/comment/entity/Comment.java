package com.example.schedule1app.comment.entity;

import com.example.schedule1app.gobal.entity.BaseEntity;
import com.example.schedule1app.schedule.entity.Schedule;
import com.example.schedule1app.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment (String contents, User user,  Schedule schedule){
        this.contents = contents;
        this.user = user;
        this.schedule =schedule;
    }
}
