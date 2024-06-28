package com.example.special.lecture.application.Lecture.db;

import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.user.controller.model.UserResponse;
import com.example.special.lecture.application.user.db.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(name = "lecture_id", nullable = false,unique = true)
    private Long lectureId;

    @Column(name = "lecture_name",nullable = false)
    private String lectureName;

    @Column(name = "lecture_capacity",nullable = false)
    private Long lectureCapacity;

    @Column(name = "current_lecture_capacity",nullable = false)
    private Long currentLectureCapacity;

    @Column(name = "apply_date",nullable = false)
    private LocalDateTime applyDate;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Version
    private Long version;



    public static LectureResponse entityToResponse(Lecture lecture){
        return LectureResponse.builder()
                .lectureId(lecture.getLectureId())
                .lectureCapacity(lecture.getLectureCapacity())
                .currentLectureCapacity(lecture.getCurrentLectureCapacity())
                .lectureName(lecture.getLectureName())
                .applyDate(lecture.applyDate)
                .beginDate(lecture.beginDate)
                .build();
    }

    public void applyLecture() throws IllegalAccessException {

        if(this.currentLectureCapacity >= this.lectureCapacity ){
            throw new IllegalAccessException("수강인원이 초과되었습니다.");
        }
        this.setCurrentLectureCapacity(this.currentLectureCapacity+1);
    }

    public void checkApplyTime(LocalDateTime localDateTime) {
        if (localDateTime.isBefore(this.applyDate)) {
            throw new IllegalArgumentException("수강신청 기간이아닙니다.");
        }

    }
}
