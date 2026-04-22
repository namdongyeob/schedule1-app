package com.example.schedule1app.auth.service;

import com.example.schedule1app.auth.dto.LoginRequest;
import com.example.schedule1app.gobal.config.PasswordEncoder;
import com.example.schedule1app.gobal.excption.InvalidPasswordException;
import com.example.schedule1app.gobal.excption.UserNotFoundException;
import com.example.schedule1app.user.entity.User;
import com.example.schedule1app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인 검증 후 User 반환
    @Transactional(readOnly = true)
    public User login(LoginRequest request) {

        // 1. 이메일로 유저 찾기 → 없으면 예외
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                UserNotFoundException::new);

        // 2. 비밀번호 일치 확인 → 다르면 예외
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        // 3. 검증 완료된 User 반환 → Controller에서 세션에 저장할 거야
        return user;
    }
}
