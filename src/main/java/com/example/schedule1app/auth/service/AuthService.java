package com.example.schedule1app.auth.service;

import com.example.schedule1app.auth.dto.LoginRequest;
import com.example.schedule1app.user.entity.User;
import com.example.schedule1app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    // 로그인 검증 후 User 반환
    public User login(LoginRequest request) {

        // 1. 이메일로 유저 찾기 → 없으면 예외
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일을 가진 유저가 없습니다."));

        // 2. 비밀번호 일치 확인 → 다르면 예외
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 검증 완료된 User 반환 → Controller에서 세션에 저장할 거야
        return user;
    }
}
