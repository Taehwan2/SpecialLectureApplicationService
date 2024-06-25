package com.example.special.lecture.application.user.db.repository;

import com.example.special.lecture.application.user.db.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity post(UserEntity user) {
        return userJpaRepository.save(user);
    }
}
