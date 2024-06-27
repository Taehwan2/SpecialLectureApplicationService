package com.example.special.lecture.application.applyhistory.service;

import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyHistoryServiceImpl implements ApplyHistoryService {


    private final ApplyHistoryRepository applicationHistoryRepository;


    @Override
    public Boolean findApplyHistoryByUserId(Long userId,Long lectureId) {
        Integer applyHistoryCount = applicationHistoryRepository.findByUserIdAndLectureId(userId,lectureId);
        return applyHistoryCount==0?false:true;
    }

    @Override
    public Boolean saveApplyHistory(ApplyHistory applyHistory) {
        return applicationHistoryRepository.save(applyHistory);

    }


    @Override
    public Boolean checkApplyHistorySameDay(Long userId, LocalDate now) {
        Integer count = applicationHistoryRepository.findByUserIdAndDate(userId,now);
        log.info("===>{}",count);
        if(count !=0){
            throw new RuntimeException("동일한 날에 수강신청된 강의가 있습니다.");
        }
        return true;
    }
}
