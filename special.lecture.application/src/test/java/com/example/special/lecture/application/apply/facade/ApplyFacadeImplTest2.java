package com.example.special.lecture.application.apply.facade;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureService;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;
import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.applyhistory.service.ApplyHistoryService;
import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyFacadeImplTest2 {
    @Autowired
    private ApplyFacadeImpl applyFacade;
    @Autowired
    private  ApplyHistoryService applyHistoryService;

    @Autowired
    private  LectureService lectureService;

    @Autowired
    private  UserService userService;
    @Test
    @DisplayName("실제 DB에 저장되어 잘 동작하는지 통합테스트")
    void checkUserApplyLectureTest() {

        //History 를 열어본다.
        ApplyHistory application = new ApplyHistory(1L,1L,1L, LocalDateTime.now());
        applyHistoryService.saveApplyHistory(application);

        //check 를 한다.
         Boolean check = applyFacade.checkUserApplyLecture(1L,1L);

         //검증한다.
         assertThat(check).isTrue();
    }

    @Test
    @DisplayName("실제 여러 날짜들이 강의들이 저장되는지 확인하는 코드")
    void findAllLectures() {
        Lecture lecture1 = new Lecture(1L, "항해", 10L, 10L, LocalDateTime.now(), LocalDate.now(), 0L);
        Lecture lecture2 = new Lecture(2L, "항해", 10L, 10L, LocalDateTime.now(), LocalDate.now(), 0L);
        lectureService.postLecture(lecture1);
        lectureService.postLecture(lecture2);

        List<Lecture> lectures = applyFacade.findAllLectures();
        assertThat(lectures.get(0).getLectureName()).isEqualTo("항해");
    }

    @Test
    @DisplayName("강의가 시작하는 날이 겹치는 경우")
    void validateAndFetchLectureTest() {
        //given Lecture 먼저 주기
        ApplyRequest applyRequest = new ApplyRequest(1L,1L,LocalDateTime.now());
        Lecture lecture1 = new Lecture(1L, "항해", 10L, 10L, LocalDateTime.now(), LocalDate.now(), 0L);
        lectureService.postLecture(lecture1);

        //검증
        Lecture resultLecture = applyFacade.validateAndFetchLecture(applyRequest);
        assertThat(resultLecture.getLectureCapacity()).isEqualTo(lecture1.getCurrentLectureCapacity());
    }

    @Test
    @DisplayName("실제 강의를 등록하는 코드 수강인원 초과로 테스트 실패")
    void applySpecialLectureTest() throws IllegalAccessException {
        /*
         userService.findByUserId(applyRequest.getUserId());

        //신청 날짜에 가능한지 확인한다.
        var findlecture =validateAndFetchLecture(applyRequest);

        //수강 신청이 가능한지 확인하고 수강신청한다.
        lectureService.changeLectureCapacity(findlecture,applyRequest.getApplyTime());

        //수강 신청이 완료되었을 때 history 를 남긴다.
        var applyEntity = ApplyHistory.builder()
                .userId(applyRequest.getUserId())
                .lectureId(applyRequest.getLectureId())
                .applyHistoryTime(applyRequest.getApplyTime())
                .build();

        //신청 여부를 Boolean type 으로 보내준다.
        return applyHistoryService.saveApplyHistory(applyEntity);
         */
        Clock fixedClock = Clock.fixed(Instant.parse("2024-01-01T10:00:00Z"), ZoneId.systemDefault());

        UserEntity user = new UserEntity(1L,"태환");
        userService.enrollUser(user);

        ApplyRequest applyRequest = new ApplyRequest(1L,1L,LocalDateTime.now(fixedClock));

        //Todo 일부로 수강인원을 다채워놓고 테스트
        Lecture lecture1 = new Lecture(1L, "항해", 10L, 10L, LocalDateTime.now(fixedClock), LocalDate.now(fixedClock), 0L);
        lectureService.postLecture(lecture1);

        applyFacade.applySpecialLecture(applyRequest);



    }

    @Test
    @DisplayName("실제 강의를 등록하는 코드 성공코드")
    void applySpecialLectureTest2() throws IllegalAccessException {

        Clock fixedClock = Clock.fixed(Instant.parse("2024-01-01T10:00:00Z"), ZoneId.systemDefault());

        UserEntity user = new UserEntity(1L,"태환");
        userService.enrollUser(user);

        ApplyRequest applyRequest = new ApplyRequest(1L,1L,LocalDateTime.now(fixedClock));
        Lecture lecture1 = new Lecture(1L, "항해", 10L, 0L, LocalDateTime.now(fixedClock), LocalDate.now(fixedClock), 0L);
        lectureService.postLecture(lecture1);

        applyFacade.applySpecialLecture(applyRequest);



    }


    @Test
    @DisplayName("실제 강의를 등록하는 코드 성공코드 now를 사용했을 때 시간이 맞지않아 수강신청 실패")
    void applySpecialLectureTest3() throws IllegalAccessException {


        UserEntity user = new UserEntity(1L,"태환");
        userService.enrollUser(user);

        ApplyRequest applyRequest = new ApplyRequest(1L,1L,LocalDateTime.now());
        Lecture lecture1 = new Lecture(1L, "항해", 10L, 0L, LocalDateTime.now(), LocalDate.now(), 0L);
        lectureService.postLecture(lecture1);

        applyFacade.applySpecialLecture(applyRequest);



    }
}