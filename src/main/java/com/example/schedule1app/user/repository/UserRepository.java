package com.example.schedule1app.user.repository;

import com.example.schedule1app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
