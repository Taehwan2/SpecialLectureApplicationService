package com.example.special.lecture.application.apply.controller;

import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;
import com.example.special.lecture.application.apply.facade.ApplyFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplyFacade applyFacade;


    @Test
    @DisplayName("통합테스트로 API 스펙검증")
    public void testGetApplyHistory() throws Exception {
        Long userId = 1L;
        Long lectureId = 1L;
        given(applyFacade.checkUserApplyLecture(userId, lectureId)).willReturn(true);

        mockMvc.perform(get("/lectures/{userId}/{lectureId}", userId, lectureId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("통합테스트로 API 스펙검증")
    public void testGetLectures() throws Exception {
        Lecture lecture1 = new Lecture(1L,"항해",10L,10L, LocalDateTime.now(), LocalDate.now(),0L);
        Lecture lecture2 = new Lecture(2L,"항해",10L,10L, LocalDateTime.now(),LocalDate.now(),0L);

        List<Lecture> lectures = Arrays.asList(lecture1, lecture2);
        List<LectureResponse> responses = lectures.stream()
                .map(Lecture::entityToResponse).toList();

        given(applyFacade.findAllLectures()).willReturn(lectures);

        mockMvc.perform(get("/lectures"))
                .andDo(print()) // 결과 출력
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(responses.size())))
                .andExpect(jsonPath("$[0].lectureId", is(lecture1.getLectureId().intValue())))
                .andExpect(jsonPath("$[0].lectureName", is(lecture1.getLectureName())))
                .andExpect(jsonPath("$[0].lectureCapacity", is(lecture1.getLectureCapacity().intValue())))
                .andExpect(jsonPath("$[0].currentLectureCapacity", is(lecture1.getCurrentLectureCapacity().intValue())));

    }

    @Test
    public void testApplySpecialLecture() throws Exception {
        ApplyRequest applyRequest = new ApplyRequest(1L,1L,LocalDateTime.now());


        String jsonRequest = String.format(
                "{\"userId\": %d, \"lectureId\": %d, \"applyTime\": \"%s\"}",
                1L, // userId
                1L, // lectureId
                LocalDateTime.now().toString() // applyTime
        );

        given(applyFacade.applySpecialLecture(any(ApplyRequest.class))).willReturn(true);

        mockMvc.perform(patch("/lectures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


}