package com.example.special.lecture.application.user.service;

import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserEntity enrollUser(UserEntity user) {

        var enrollUser = userRepository.post(user);
        log.info("userId: {}",user.getUserId());
        return enrollUser;
    }
}
