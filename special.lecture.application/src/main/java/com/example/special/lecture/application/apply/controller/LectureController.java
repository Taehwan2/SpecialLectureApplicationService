package com.example.special.lecture.application.apply.controller;

import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;

import com.example.special.lecture.application.apply.facade.ApplyFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/lectures")
public class LectureController {

    private final ApplyFacade applyService;

    @GetMapping("/{userId}/{lectureId}")
    public  Boolean getApplyHistory(@PathVariable(name = "userId") Long userId,@PathVariable(name = "lectureId")Long lectureId) {
        return applyService.checkUserApplyLecture(userId,lectureId);
    }

    @GetMapping("")
    public  List<LectureResponse> getLectures() {
        return applyService.findAllLectures().stream()
                .map(Lecture::entityToResponse)
                .collect(Collectors.toList());
    }


    @PostMapping("")
    public Boolean applySpecialLecture(@RequestBody  ApplyRequest applyRequest) throws IllegalAccessException {
        return applyService.applySpecialLecture(applyRequest);
    }
}
