package com.example.special.lecture.application.user.service;

import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.db.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("간단한 유저 post 테스트")
    void enrollUser() {
        UserEntity user = new UserEntity(1L,"태환");

        //given
        given(userRepository.post(user)).willReturn(user);

        //when
        UserEntity result = userService.enrollUser(user);

        //Then
        assertThat(result.getUserId()).isEqualTo(user.getUserId());
        assertThat(result.getUserName()).isEqualTo(user.getUserName());
    }
}