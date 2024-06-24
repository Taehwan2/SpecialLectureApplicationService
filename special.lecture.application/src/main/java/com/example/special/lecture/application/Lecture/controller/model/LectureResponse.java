package com.example.special.lecture.application.Lecture.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class LectureResponse {
    private Long lectureId;

    private String lectureName;

    private Long lectureCapacity;

    private Long currentLectureCapacity;

    private LocalDateTime applyDate;
}
