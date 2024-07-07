package com.example.special.lecture.application.history.service;

import com.example.special.lecture.application.applyhistory.db.ApplyHistory;
import com.example.special.lecture.application.applyhistory.service.ApplyHistoryRepository;
import com.example.special.lecture.application.applyhistory.service.ApplyHistoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @InjectMocks
    private ApplyHistoryServiceImpl applyHistoryService;

    @Mock
    private ApplyHistoryRepository applyHistoryRepository;

    @Test
    @DisplayName("userId로 유저가 신청한 특강의 정보를 불러올 수 있는 테스트")
    void findApplyHistoryByUserIdTest() {



        //given
        given(applyHistoryRepository.findByUserIdAndLectureId(1L,1L)).willReturn(1);

        //when
        Boolean check= applyHistoryService.findApplyHistoryByUserId(1L,1L);

        //then
        assertThat(check).isEqualTo(true);

    }

    @Test
    @DisplayName("application 을 저장했을 경우에 저장의 성공유무를 반환해주는 코드")
    void saveApplyHistoryTest() {
        ApplyHistory application = new ApplyHistory();

        //given
        given(applyHistoryRepository.save(application)).willReturn(true);

        //when
        Boolean check = applyHistoryService.saveApplyHistory(application);

        //then
        assertThat(check).isTrue();
    }

    @Test
    @DisplayName("동일한 날짜에 있는 지 확인해 주는코드")
    void checkApplyHistorySameDayTest() {
        // given
        given(applyHistoryRepository.findByUserIdAndDate(1L, LocalDate.now())).willReturn(0);

        //when
        Boolean check  =  applyHistoryService.checkApplyHistorySameDay(1L, LocalDate.now());

        //then
        assertThat(check).isTrue();
    }

    @Test
    @DisplayName("동일한 날짜에 있는 지 확인해 주는코드 이셉션 발생")
    void checkApplyHistorySameDayTest2() {
        //given
        given(applyHistoryRepository.findByUserIdAndDate(1L, LocalDate.now())).willReturn(1);
        //when
        assertThrows( RuntimeException.class , () -> {
            Boolean check = applyHistoryService.checkApplyHistorySameDay(1L, LocalDate.now());
        });
    }
}