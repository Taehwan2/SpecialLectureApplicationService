package com.example.special.lecture.application.controller;

import com.example.special.lecture.application.controller.model.LectureRequest;
import com.example.special.lecture.application.controller.model.LectureResponse;
import com.example.special.lecture.application.service.ApplyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class LectureControllerTest {
    /* Todo
         - 특강 신청 API
         - 특강 신청 여부 조회 API
     */

    private LectureController lectureController;


    @BeforeEach
    void setUp() {
        lectureController = new LectureController();
    }

    @Test
    @DisplayName("userId를 다른 userId를 전달했을 떄 나오는 결과값 테스트")
    public void getApplicationByIdTest() {
        Long userId = 2L;
        Boolean trueOrFalse = lectureController.checkWhetherToApply(userId);
        assertThat(trueOrFalse).isEqualTo(true);
    }

    @Test
    @DisplayName("특강을 신청하는 메서드 테스트")
    public void applySpecialLectureTest() {
        LectureRequest lectureRequest = new LectureRequest();
        LectureResponse lectureResponse = lectureController.applySpecialLecture(lectureRequest);
        assertThat(lectureResponse).isInstanceOf(LectureResponse.class);
    }

}