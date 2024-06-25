package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;

public interface LectureService {
    /*TODO
    요구사항
    1. 강의 찾기

    2.강의 등록하기

    3.강의 수용인원 변경하기 -> 추후 진행
     */

    Lecture findLectureById(Long lectureId);

    Lecture postLecture(Lecture lecture);

    Lecture changeLectureCapacity(Long lectureId) throws IllegalAccessException;

}
