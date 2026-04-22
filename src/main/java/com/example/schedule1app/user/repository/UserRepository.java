package com.example.schedule1app.user.repository;

import com.example.schedule1app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 유저를 찾는 쿼리 메서드 추가
    Optional<User> findByEmail(String email);
}
