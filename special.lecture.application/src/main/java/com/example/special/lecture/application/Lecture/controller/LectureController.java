package com.example.special.lecture.application.Lecture.controller;


import com.example.special.lecture.application.Lecture.controller.model.LectureRequest;
import com.example.special.lecture.application.Lecture.controller.model.LectureResponse;
import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureService;
import com.example.special.lecture.application.user.controller.model.UserRequest;
import com.example.special.lecture.application.user.controller.model.UserResponse;
import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;

/*    @PostMapping("")
    public UserResponse enrolllecture(@RequestBody LectureRequest lectureRequest){
        var lecture = LectureRequest.requestToEntity(lectureRequest);
        var resultlecture= lectureService.enrollLecture(lecture);
        return Lecture.entityToResponse(resultlecture);
    }*/
}
