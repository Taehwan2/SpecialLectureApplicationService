package com.example.special.lecture.application.apply.facade;

import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureService;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;

import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.applyhistory.service.ApplyHistoryService;
import com.example.special.lecture.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyFacadeImpl implements ApplyFacade {

    private final ApplyHistoryService applyHistoryService;

    private final LectureService lectureService;

    private final UserService userService;

    @Override
    public Boolean checkUserApplyLecture(Long userId,Long lectureId) {

        //user 가 신청한 모든 강의 목록을 가져온다.
        return applyHistoryService.findApplyHistoryByUserId(userId,lectureId);
    }

    public List<Lecture> findAllLectures(){
        return lectureService.findLectures();
    }

    @Override
    public Lecture validateAndFetchLecture(ApplyRequest applyRequest) {

        //Lecture 에 대한 정보를 가져온다.
        Lecture findLecture = lectureService.findLectureById(applyRequest.getLectureId());

        //Lecture 가 시작되는 날과 동일한 날의 강의가 있는 지를 판별한다.
        applyHistoryService.checkApplyHistorySameDay(applyRequest.getUserId(), findLecture.getBeginDate());
        return findLecture;
    }

    @Override
    @Transactional
    public Boolean applySpecialLecture(ApplyRequest applyRequest) throws IllegalAccessException {

        //신청한 유저가 있는 지 확인한다.
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
    }


}
