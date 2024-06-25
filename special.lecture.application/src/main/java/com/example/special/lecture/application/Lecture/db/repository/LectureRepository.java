package com.example.special.lecture.application.Lecture.db.repository;

import com.example.special.lecture.application.Lecture.db.Lecture;

import java.util.Optional;

public interface LectureRepository {
    Optional<Lecture> findLectureById(Long lectureId);

    Lecture save(Lecture lecture);
}
