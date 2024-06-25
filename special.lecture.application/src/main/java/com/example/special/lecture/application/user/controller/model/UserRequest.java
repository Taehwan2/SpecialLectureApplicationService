package com.example.special.lecture.application.user.controller.model;

import com.example.special.lecture.application.user.db.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserRequest {

    @NotBlank
    private String userName;


    public static UserEntity requestToEntity(UserRequest userRequest){
        return UserEntity.builder()
                .userName(userRequest.userName)
                .build();
    }
}
