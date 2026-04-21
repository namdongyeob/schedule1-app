package com.example.schedule1app.user.service;

import com.example.schedule1app.user.dto.*;
import com.example.schedule1app.user.entity.User;
import com.example.schedule1app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        User user = new User(request.getUsername(), request.getEmail());
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(GetUserResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetUserResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 찾을수 없습니다."));
        return GetUserResponse.from(user);


    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 찾을수 없습니다."));
        user.update(request.getUsername(), request.getEmail());
        return UpdateUserResponse.from(user);
    }

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
        userRepository.delete(user);
    }
}
