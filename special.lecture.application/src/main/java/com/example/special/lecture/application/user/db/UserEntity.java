package com.example.special.lecture.application.user.db;

import com.example.special.lecture.application.user.controller.model.UserResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_entity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;


    public static UserResponse entityToResponse(UserEntity user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }
}
