package com.example.special.lecture.application.controller;

import com.example.special.lecture.application.controller.model.LectureRequest;
import com.example.special.lecture.application.controller.model.LectureResponse;
import com.example.special.lecture.application.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class LectureController {

    //private final ApplyService applyService;
    public Boolean checkWhetherToApply(Long userId) {
        if(userId==1L) return true;
        return false;
    }

    public LectureResponse applySpecialLecture(LectureRequest lectureRequest) {
        return new LectureResponse();
    }
}
