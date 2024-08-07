package com.example.special.lecture.application.Lecture.db.repository;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJPARepository lectureJPARepository;


    @Override
    public Optional<Lecture> findLectureById(Long lectureId) {
        return lectureJPARepository.findById(lectureId);
    }

    @Override
    public List<Lecture> findLectures() {
        return lectureJPARepository.findAll();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJPARepository.save(lecture);
    }
}
