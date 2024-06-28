package com.example.special.lecture.application.user.service;

import com.example.special.lecture.application.user.db.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("간단한 유저 post 테스트")
    void enrollUserTest() {
        UserEntity user = new UserEntity(1L,"태환");

        //given
        given(userRepository.post(user)).willReturn(user);

        //when
        UserEntity result = userService.enrollUser(user);

        //Then
        assertThat(result.getUserId()).isEqualTo(user.getUserId());
        assertThat(result.getUserName()).isEqualTo(user.getUserName());
    }

    @Test
    @DisplayName("요구사항 중 userId를 통해서 수강신청을 위한 UserEntity 반환하는 코드 작성")
    void findUserByUserIdTest(){

        UserEntity user = new UserEntity(1L,"태환");

        //given
        //Todo userId를 통해서 User 를 가져오는 로직
        given(userRepository.findUserByUserId(1L)).willReturn(user);

        //when
        UserEntity result = userService.findByUserId(1L);

        //Then
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getUserName()).isEqualTo("태환");

    }

    @Test
    @DisplayName("요구사항 중 userId를 통해서 없느 UserEntity 반환하는 코드 작성")
    void findUserByUserIdTest2(){

        //given
        //Todo userId를 통해서 User 를 가져오는 로직
        given(userRepository.findUserByUserId(2L)).willThrow(new NoSuchElementException("사용자가 없습니다."));

        //when & then
        assertThrows(NoSuchElementException.class, () -> {
            UserEntity result = userService.findByUserId(2L);
        });



    }



}