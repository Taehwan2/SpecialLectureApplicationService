package com.example.special.lecture.application.Lecture.db;

import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.user.controller.model.UserResponse;
import com.example.special.lecture.application.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    private String lectureName;

    private Long lectureCapacity;

    private Long currentLectureCapacity;


    public static LectureResponse entityToResponse(Lecture lecture){
        return LectureResponse.builder()
                .lectureId(lecture.getLectureId())
                .lectureCapacity(lecture.getLectureCapacity())
                .currentLectureCapacity(lecture.getCurrentLectureCapacity())
                .lectureName(lecture.getLectureName())
                .build();
    }
}
