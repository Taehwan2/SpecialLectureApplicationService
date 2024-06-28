package com.example.special.lecture.application.user.service;

import com.example.special.lecture.application.user.db.UserEntity;

public interface UserRepository {
    UserEntity post(UserEntity user);

    UserEntity findUserByUserId(Long userId);
}
