package com.example.special.lecture.application.user.service;

import com.example.special.lecture.application.user.db.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity enrollUser(UserEntity user);

    UserEntity findByUserId(Long userId);
}
