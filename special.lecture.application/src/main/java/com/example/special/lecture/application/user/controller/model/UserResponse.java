package com.example.special.lecture.application.user.controller.model;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;

    private String userName;
}
