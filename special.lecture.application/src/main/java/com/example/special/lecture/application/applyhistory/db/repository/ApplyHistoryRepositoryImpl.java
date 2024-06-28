package com.example.special.lecture.application.applyhistory.db.repository;

import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.applyhistory.db.repository.ApplyHistoryJPARepository;

import com.example.special.lecture.application.applyhistory.service.ApplyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyHistoryRepositoryImpl implements ApplyHistoryRepository {

    private final ApplyHistoryJPARepository applyHistoryJPARepository;

    @Override
    public Boolean save(ApplyHistory applyHistory) {
        var history = applyHistoryJPARepository.save(applyHistory);
        return true;
    }

    @Override
    public Integer findByUserIdAndLectureId(long userId,Long lectureId) {
        return applyHistoryJPARepository.countApplyHistoriesByUserIdAndLectureId(userId,lectureId);
    }

    @Override
    public Integer findByUserIdAndDate(Long userId, LocalDate localDate) {
        return  applyHistoryJPARepository.findByUserIdAndApplyHistoryTime(userId,localDate);
    }
}
