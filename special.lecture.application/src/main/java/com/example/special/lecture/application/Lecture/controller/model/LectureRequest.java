package com.example.special.lecture.application.Lecture.controller.model;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.user.controller.model.UserRequest;
import com.example.special.lecture.application.user.db.UserEntity;
import lombok.Getter;

@Getter
public class LectureRequest {
    private String lectureName;

    private Long lectureCapacity;

    private Long currentLectureCapacity;


    public static Lecture requestToEntity(LectureRequest lectureRequest){
        return Lecture.builder()
                .lectureCapacity(lectureRequest.getLectureCapacity())
                .lectureName(lectureRequest.getLectureName())
                .currentLectureCapacity(lectureRequest.getCurrentLectureCapacity())
                .build();
    }
}
