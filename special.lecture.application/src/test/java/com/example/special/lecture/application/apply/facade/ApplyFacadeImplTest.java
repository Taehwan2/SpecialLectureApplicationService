package com.example.special.lecture.application.apply.facade;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureService;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;
import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.applyhistory.service.ApplyHistoryService;
import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplyFacadeImplTest {

    @Mock
    private ApplyHistoryService applyHistoryService;

    @Mock
    private LectureService lectureService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ApplyFacadeImpl applyFacade;



    @Test
    @DisplayName("userId와 lectureId를 가지고 저장이 되었는가 확인하는 메서드")
    void testCheckUserApplyLectureTest() {
        // Setup
        Long userId = 1L;
        Long lectureId = 1L;
        given(applyHistoryService.findApplyHistoryByUserId(userId, lectureId)).willReturn(true);

        // when
        Boolean result = applyFacade.checkUserApplyLecture(userId, lectureId);

        // then
        assertTrue(result);
        assertThat(result).isTrue();
        verify(applyHistoryService).findApplyHistoryByUserId(userId, lectureId);
    }

    @Test
    @DisplayName("userId와 lectureId를 가지고 저장이 되었는가 확인하는 메서드(없으면 false)")
    void testCheckUserApplyLectureTest2() {
        // Setup
        Long userId = 1L;
        Long lectureId = 1L;
        given(applyHistoryService.findApplyHistoryByUserId(userId, lectureId)).willReturn(false);

        // when
        Boolean result = applyFacade.checkUserApplyLecture(userId, lectureId);

        // then
        assertTrue(result);
        assertThat(result).isFalse();
        verify(applyHistoryService).findApplyHistoryByUserId(userId, lectureId);
    }

    @Test
    @DisplayName("userId와 lectureId를 가지고 저장이 되었는가 확인하는 메서드")
    void testFindAllLectures() {
        // given
        // Lecture 1 객체 생성 및 속성 설정
        Lecture lecture1 = Lecture.builder()
                .lectureId(1L)
                .lectureName("Programming with Java")
                .lectureCapacity(30L)
                .currentLectureCapacity(25L)
                .applyDate(LocalDateTime.of(2023, 9, 1, 9, 0))
                .beginDate(LocalDate.of(2023, 10, 1))
                .build();

        // Lecture 2 객체 생성 및 속성 설정
        Lecture lecture2 = Lecture.builder()
                .lectureId(2L)
                .lectureName("Introduction to AI")
                .lectureCapacity(40L)
                .currentLectureCapacity(20L)
                .applyDate(LocalDateTime.of(2023, 8, 1, 9, 0))
                .beginDate(LocalDate.of(2023, 9, 1))
                .build();
        given(lectureService.findLectures()).willReturn(Arrays.asList(lecture1, lecture2));

        // when
        List<Lecture> lectures = applyFacade.findAllLectures();

        // Verify
        //then
        assertEquals(2, lectures.size());
        assertThat(lectures.get(0).getLectureName()).isIn(lecture1.getLectureName(),lecture2.getLectureName());
        verify(lectureService).findLectures();
    }

    @Test
    @DisplayName("같은날에 특강 이있는지 확인하는 테스트 ")
    void testValidateAndFetchLectureTest() throws IllegalAccessException {
        // given
        ApplyRequest applyRequest = new ApplyRequest(1L,1L,LocalDateTime.now());


        Lecture lecture = new Lecture(); // Set properties as needed
        given(lectureService.findLectureById(1L)).willReturn(lecture);
        given(applyHistoryService.checkApplyHistorySameDay(1L, lecture.getBeginDate())).willReturn(true);

        // when
        Lecture result = applyFacade.validateAndFetchLecture(applyRequest);

        // then
        assertNotNull(result);
        verify(lectureService).findLectureById(1L);
        verify(applyHistoryService).checkApplyHistorySameDay(1L, lecture.getBeginDate());
    }

    @Test
    @DisplayName("특강을 신청하는 로직 -> 각 서비스를 단위테스트로 작성하여 테스트했음으로 성공케이스 테스트하고 통합테스트로 이동")
    void testApplySpecialLectureTest() throws IllegalAccessException {
        // given
        ApplyRequest applyRequest = new ApplyRequest(1L, 1L, LocalDateTime.now());
        UserEntity userEntity = new UserEntity(); // Assume UserEntity has necessary properties
        Lecture lecture = new Lecture(1L, "Programming with Java", 30L, 25L, LocalDateTime.of(2023, 9, 1, 9, 0), LocalDate.of(2023, 10, 1), null);

        given(userService.findByUserId(1L)).willReturn(userEntity);
        given(lectureService.findLectureById(1L)).willReturn(lecture);
        given(lectureService.changeLectureCapacity(eq(lecture), any(LocalDateTime.class))).willReturn(lecture);
        given(applyHistoryService.saveApplyHistory(any(ApplyHistory.class))).willReturn(true);

        // when
        Boolean result = applyFacade.applySpecialLecture(applyRequest);

        // then
        assertTrue(result);
        verify(userService).findByUserId(1L);
        verify(lectureService).findLectureById(1L);
        verify(lectureService).changeLectureCapacity(eq(lecture), any(LocalDateTime.class));
        verify(applyHistoryService).saveApplyHistory(any(ApplyHistory.class));
    }
}