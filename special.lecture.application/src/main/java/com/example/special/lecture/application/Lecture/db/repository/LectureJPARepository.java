package com.example.special.lecture.application.Lecture.db.repository;

import com.example.special.lecture.application.Lecture.db.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJPARepository extends JpaRepository<Lecture,Long> {
}
