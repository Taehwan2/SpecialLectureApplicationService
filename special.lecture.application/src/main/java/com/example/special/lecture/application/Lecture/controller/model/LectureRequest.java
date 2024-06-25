package com.example.special.lecture.application.Lecture.controller.model;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.user.controller.model.UserRequest;
import com.example.special.lecture.application.user.db.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureRequest {
    private String lectureName;

    private Long lectureCapacity;

    private Long currentLectureCapacity;

    //요구사항 추가
    private LocalDateTime applyDate;

    public static Lecture requestToEntity(LectureRequest lectureRequest){
        return Lecture.builder()
                .lectureCapacity(lectureRequest.getLectureCapacity())
                .lectureName(lectureRequest.getLectureName())
                .currentLectureCapacity(lectureRequest.getCurrentLectureCapacity())
                .applyDate(lectureRequest.applyDate)
                .build();
    }
}
