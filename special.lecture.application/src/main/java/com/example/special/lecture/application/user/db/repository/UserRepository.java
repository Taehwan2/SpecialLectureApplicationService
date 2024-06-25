package com.example.special.lecture.application.user.db.repository;

import com.example.special.lecture.application.user.db.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity post(UserEntity user);
}
