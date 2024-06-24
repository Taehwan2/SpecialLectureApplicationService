package com.example.special.lecture.application.Lecture.db.repository;

import com.example.special.lecture.application.Lecture.db.Lecture;

public interface LectureRepository {
    Lecture findLectureById(Long lectureId);

    Lecture save(Lecture lecture);
}
