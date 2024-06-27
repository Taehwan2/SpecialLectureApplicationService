package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    @Override
    public Lecture findLectureById(Long lectureId) {
        return lectureRepository.findLectureById(lectureId).orElseThrow(() -> new NoSuchElementException("강좌가 없습니다."));
    }

    @Override
    public Lecture postLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public List<Lecture> findLectures(){
        return  lectureRepository.findLectures();
    }

    //TODO
    //TODO retry
    @Override
    public Lecture changeLectureCapacity(Lecture findLecture, LocalDateTime localDateTime) throws IllegalAccessException, ObjectOptimisticLockingFailureException {
        findLecture.checkApplyTime(localDateTime);
        findLecture.applyLecture();
        try {
            return postLecture(findLecture);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new ObjectOptimisticLockingFailureException("Optimistic lock exception", new RuntimeException("kk"));
        }

    }


}
