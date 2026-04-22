package com.example.schedule1app.auth.controller;

import com.example.schedule1app.auth.dto.LoginRequest;
import com.example.schedule1app.auth.service.AuthService;
import com.example.schedule1app.user.entity.User;
import com.example.schedule1app.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    // UserRepository는 여기서 안 쓰니까 삭제해도 돼! ←

    // 로그인 API - POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginRequest request,  // 클라이언트가 보낸 이메일+비밀번호
            HttpSession httpSession) {          // 서버가 관리하는 세션

        // 1. Service에서 이메일+비밀번호 검증 후 User 받아오기
        User user = authService.login(request);

        // 2. 세션에 userId 저장 → 이후 요청마다 로그인 여부 확인에 사용
        httpSession.setAttribute("userId", user.getId());

        // 3. 클라이언트에게 200 OK만 반환 (Session 방식은 Response 데이터 불필요)
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 로그아웃 API - POST /auth/logout
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {

        // 세션 삭제 → 로그아웃 처리 (다음 요청부터 세션 없음)
        httpSession.invalidate();

        // 200 OK 반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
