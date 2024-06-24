package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.db.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService{

    private LectureRepository lectureRepository;

    @Override
    public Lecture findLectureById(Long lectureId) {
        return lectureRepository.findLectureById(lectureId);
    }

    @Override
    public Lecture postLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture changeLectureCapacity(Long lectureId) {

        return null;
    }
}
