package com.example.special.lecture.application.apply.facade;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;

import java.util.List;

public interface ApplyFacade {

    Lecture validateAndFetchLecture(ApplyRequest applyRequest);
    Boolean applySpecialLecture(ApplyRequest applyRequest) throws IllegalAccessException;
    Boolean checkUserApplyLecture(Long userId,Long lectureId);
    List<Lecture> findAllLectures();
}
