package com.example.special.lecture.application.applyhistory.service;


import com.example.special.lecture.application.applyhistory.db.ApplyHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ApplyHistoryService {

    Boolean findApplyHistoryByUserId(Long userId,Long lectureId);

    Boolean saveApplyHistory(ApplyHistory applyHistory);

    Boolean checkApplyHistorySameDay(Long userId, LocalDate now);
}
