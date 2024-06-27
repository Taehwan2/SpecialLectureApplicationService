package com.example.special.lecture.application.Lecture.controller;


import com.example.special.lecture.application.Lecture.controller.model.LectureRequest;
import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureApiController {
    private final LectureService lectureService;

    @PostMapping("")
    public LectureResponse enrollLecture(@RequestBody LectureRequest lectureRequest){
        var lecture = LectureRequest.requestToEntity(lectureRequest);
        var resultlecture= lectureService.postLecture(lecture);
        return Lecture.entityToResponse(resultlecture);
    }

    @GetMapping("/{id}")
    public LectureResponse getLecture(@PathVariable Long id){
        var resultLecture = lectureService.findLectureById(id);
        return Lecture.entityToResponse(resultLecture);
    }

    @PatchMapping("/{id}")
    public LectureResponse updateLecture(@PathVariable Long id) throws IllegalAccessException {
        var resultLecture = lectureService.changeLectureCapacity(lectureService.findLectureById(id), LocalDateTime.now());
        return Lecture.entityToResponse(resultLecture);
    }

}
