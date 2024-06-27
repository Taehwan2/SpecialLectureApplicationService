package com.example.special.lecture.application.apply.facade;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.service.LectureService;
import com.example.special.lecture.application.apply.controller.model.ApplyRequest;
import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.user.db.UserEntity;
import com.example.special.lecture.application.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.time.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ApplyCurrencyTest {
    @Autowired
    private  ApplyFacade applyFacade;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private UserService userService;
    @Test
    @DisplayName("동시성 테스트 결과는 수강인원이 10명으로 채워져야하고, 등록된 사람도 10명이 되어야한다. 등록성공.")
    public void test2() throws InterruptedException, IllegalAccessException {
        {
            Clock fixedClock = Clock.fixed(Instant.parse("2024-01-01T10:00:00Z"), ZoneId.systemDefault());
            //Todo 수강신청을 위해서 일단 시간은 고정
            Lecture lecture =lectureService.postLecture(new Lecture(1L, "항해++", 10L, 0L, LocalDateTime.now(fixedClock), LocalDate.now(fixedClock),0L));


            int threadCount =1000;
            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            //CountDownLatch latch = new CountDownLatch(threadCount);

            for (int i = 0; i < threadCount; i++) {
                long userId = 1 + i;
                executorService.execute(() -> {
                    try {
                        //Todo 랜덤으로 여러 유저 저장
                        userService.enrollUser(new UserEntity(userId,"태환"));


                        ApplyRequest applyRequest = new ApplyRequest(userId,1L,LocalDateTime.now(fixedClock));

                        //실제 저장로직
                        applyFacade.applySpecialLecture(applyRequest);
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
