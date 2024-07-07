package com.example.special.lecture.application.applyhistory.db.repository;

import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ApplyHistoryJPARepository extends JpaRepository<ApplyHistory,Long> {



    Integer countApplyHistoriesByUserIdAndLectureId(long userId,Long lectureId);

    @Query("SELECT COUNT(a) FROM ApplyHistory a JOIN Lecture l on a.lectureId = l.lectureId WHERE a.userId = :userId AND DATE(l.beginDate) = :localDate")
    Integer findByUserIdAndApplyHistoryTime(@Param("userId") Long userId, @Param("localDate") LocalDate localDate);
}
