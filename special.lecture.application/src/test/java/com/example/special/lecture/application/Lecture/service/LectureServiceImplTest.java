package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LectureServiceImplTest {

    @Mock
    private LectureRepository lectureRepository;
    @InjectMocks
    private LectureServiceImpl lectureService;

    @Test
    @DisplayName("LectureId를 통해서 Lecture 가져오기")
    void findLectureByIdTest() {
        Lecture lecture = new Lecture(1L,"항해",10L,0L, LocalDateTime.now(),LocalDate.now(), 0L);

        //given
        given(lectureRepository.findLectureById(1L)).willReturn(Optional.of(lecture));

        //when
        var resultLecture = lectureService.findLectureById(1L);

        //then
        assertThat(resultLecture.getLectureName()).isEqualTo("항해");
    }


    @Test
    @DisplayName("LectureId를 통해서 없는 Lecture 가져오기")
    void findLectureByIdTest2() {

        //given
        given(lectureRepository.findLectureById(1L)).willReturn(Optional.empty());

        //when & then
        assertThrows(NoSuchElementException.class , ()->{
            var resultLecture = lectureService.findLectureById(1L);
        } );

    }


    @Test
    @DisplayName("Lecture를 DB에 저장하기")
    void postLectureTest() {
        Lecture lecture = new Lecture(1L,"항해",10L,10L, LocalDateTime.now(),LocalDate.now(),0L);

        //given
        given(lectureRepository.save(lecture)).willReturn(lecture);

        //when
        var resultLecture = lectureService.postLecture(lecture);

        //then
        assertThat(resultLecture.getLectureName()).isEqualTo(lecture.getLectureName());
        assertThat(resultLecture.getLectureCapacity()).isEqualTo(10L);
    }


    @Test
    @DisplayName("요구사항1 수강신청하기에 필수적인 요소")
    void changeLectureCapacityTest() throws IllegalAccessException {
        Lecture lecture = new Lecture(1L,"항해",10L,0L, LocalDateTime.now(),LocalDate.now(),0L);
        /*
        Long capacity = lecture.getCurrentLectureCapacity() - 1;
        if(capactiry<0){
            throw Exption
        }
        */
        //-1이 나오는지 검토해야함.
        Lecture updateLecture = new Lecture(1L,"항해",10L,1L, LocalDateTime.now(),LocalDate.now(),0L);
        given(lectureRepository.save(lecture)).willReturn(updateLecture);

        Lecture resultLecture =  lectureService.changeLectureCapacity(lecture,LocalDateTime.now());

        assertThat(resultLecture.getCurrentLectureCapacity()).isEqualTo(updateLecture.getCurrentLectureCapacity());
    }
    @Test
    @DisplayName("수강인원을 줄이는 테스트")
    void changeLectureCapacityTest2() throws IllegalAccessException {
        // given
        Lecture lecture = new Lecture(1L,"항해",10L,0L, LocalDateTime.now(),LocalDate.now(),0L);

        Lecture updateLecture = new Lecture(1L,"항해",10L,1L, LocalDateTime.now(),LocalDate.now(),0L);
        given(lectureRepository.save(lecture)).willReturn(updateLecture);

        // when
        Lecture resultLecture =  lectureService.changeLectureCapacity(lecture,LocalDateTime.now());

        // then
        assertThat(resultLecture.getCurrentLectureCapacity()).isEqualTo(1L);
    }


    @Test
    @DisplayName("수강인원이 초과되었을 때 예외를 발생시켜야 한다")
    void changeLectureCapacityShouldThrowExceptionWhenCapacityIsExceededTest() {
        // given
        //Todo currentLectureCapcity 가 0일경우에는 예외를 처리한다.
        Lecture lecture = new Lecture(1L,"항해",10L,10L, LocalDateTime.now(),LocalDate.now(),0L);

        // when & then
        assertThrows(IllegalAccessException.class, () -> {
            lectureService.changeLectureCapacity(lecture,LocalDateTime.now());
        });
    }

    @Test
    @DisplayName("어떤 특강이 있는지 한번에 불러오는 테스트")
    void getAllLecture(){

        Lecture lecture1 = new Lecture(1L,"항해",10L,10L, LocalDateTime.now(),LocalDate.now(),0L);
        Lecture lecture2 = new Lecture(2L,"항해",10L,10L, LocalDateTime.now(),LocalDate.now(),0L);
        List<Lecture> lectures = List.of(lecture1,lecture2);
        //given
        given(lectureRepository.findLectures()).willReturn(lectures);

        List<Lecture> lectureList = lectureService.findLectures();

        assertThat(lectureList).isEqualTo(lectures);


    }
}