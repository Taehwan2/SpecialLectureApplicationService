package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.db.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService{

    private final LectureRepository lectureRepository;

    @Override
    public Lecture findLectureById(Long lectureId) {
        return lectureRepository.findLectureById(lectureId).orElseThrow(()->new NoSuchElementException("강좌가 없습니다."));
    }

    @Override
    public Lecture postLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture changeLectureCapacity(Long lectureId) throws IllegalAccessException {
        var findLecture = findLectureById(lectureId);
        Long capacity = findLecture.getCurrentLectureCapacity() - 1;

        if(capacity < 0 ){
            throw new IllegalAccessException("수강인원이 초과되었습니다.");
        }

        findLecture.setCurrentLectureCapacity(capacity);
        return postLecture(findLecture);

    }
}
