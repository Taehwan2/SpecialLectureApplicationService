package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    Optional<Lecture> findLectureById(Long lectureId);
    List<Lecture> findLectures();
    Lecture save(Lecture lecture);
}
