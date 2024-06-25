package com.example.special.lecture.application.user.controller;

import com.example.special.lecture.application.user.controller.model.UserRequest;
import com.example.special.lecture.application.user.controller.model.UserResponse;
import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping("")
    public UserResponse enrollUser(@RequestBody UserRequest userRequest){
        var useEntity = UserRequest.requestToEntity(userRequest);
        var resultUser = userService.enrollUser(useEntity);
        return UserEntity.entityToResponse(resultUser);
    }
}
