package com.example.special.lecture.application.user.db.repository;

import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity post(UserEntity user) {
        return userJpaRepository.save(user);
    }

    @Override
    public UserEntity findUserByUserId(Long userId) {
        return userJpaRepository.findById(userId).orElseThrow(()->  new NoSuchElementException("사용자가 없습니다."));
    }
}
