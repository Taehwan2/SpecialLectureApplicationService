package com.example.special.lecture.application.applyhistory.service;

import com.example.special.lecture.application.applyhistory.db.ApplyHistory;


import java.time.LocalDate;
import java.util.List;

public interface ApplyHistoryRepository {
    Boolean save(ApplyHistory applyHisory);

    Integer findByUserIdAndLectureId(long userId,Long lectureId);

    Integer findByUserIdAndDate(Long userId, LocalDate localDate);
}
