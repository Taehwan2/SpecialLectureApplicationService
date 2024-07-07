package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.applyhistory.service.ApplyHistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LectureCurrencyTest {
    @Autowired
    private ApplyHistoryService applyHistoryService;

    @Autowired
    private LectureService lectureService;

    @Test
    public void test() throws InterruptedException, IllegalAccessException {
        // 동시성 테스트를 위한 스레드 풀 생성
        Lecture lecture =lectureService.postLecture(new Lecture(1L, "항해++", 10L, 10L, LocalDateTime.now(),LocalDate.now(),1L));

        for (int i = 0; i < 10; i++) {
            long userId = 1 + i;
            lectureService.changeLectureCapacity(lecture,LocalDateTime.now());
            applyHistoryService.saveApplyHistory(new ApplyHistory(userId, userId, 1L, LocalDateTime.now()));

        }
    }


    @Test
    @DisplayName("no currncy")
    public void test2() throws InterruptedException, IllegalAccessException {
        {
            Lecture lecture =lectureService.postLecture(new Lecture(1L, "항해++", 10L, 0L, LocalDateTime.now(),LocalDate.now(),0L));


            int threadCount =1000;
            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            //CountDownLatch latch = new CountDownLatch(threadCount);

            for (int i = 0; i < threadCount; i++) {
                long userId = 1 + i;
                executorService.execute(() -> {
                    try {
                        var findlecture = lectureService.findLectureById(1L);
                        lectureService.changeLectureCapacity(findlecture,LocalDateTime.now());
                        applyHistoryService.saveApplyHistory(new ApplyHistory(userId, userId, 1L, LocalDateTime.now()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }catch (ObjectOptimisticLockingFailureException o){
                        throw new RuntimeException(o);
                    }
                });
            }
            // latch.await();
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);

        }
    }

}

