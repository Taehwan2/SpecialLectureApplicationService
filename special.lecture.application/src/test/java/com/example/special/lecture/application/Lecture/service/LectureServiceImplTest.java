package com.example.special.lecture.application.Lecture.service;

import com.example.special.lecture.application.Lecture.db.Lecture;
import com.example.special.lecture.application.Lecture.db.repository.LectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LectureServiceImplTest {

    @Mock
    private LectureRepository lectureRepository;
    @InjectMocks
    private LectureServiceImpl lectureService;

    @Test
    @DisplayName("LectureId를 통해서 Lecture 가져오기")
    void findLectureById() {
        Lecture lecture = new Lecture(1L,"항해",10L,10L);

        //given
        given(lectureRepository.findLectureById(1L)).willReturn(lecture);

        //when
        var resultLecture = lectureService.findLectureById(1L);

        //then
        assertThat(resultLecture.getLectureName()).isEqualTo(lecture.getLectureName());
    }

    @Test
    @DisplayName("Lecture를 DB에 저장하기")
    void postLecture() {
        Lecture lecture = new Lecture(1L,"항해",10L,10L);

        //given
        given(lectureRepository.save(lecture)).willReturn(lecture);

        //when
        var resultLecture = lectureService.postLecture(lecture);

        //then
        assertThat(resultLecture.getLectureName()).isEqualTo(lecture.getLectureName());
        assertThat(resultLecture.getLectureCapacity()).isEqualTo(100L);
    }

    @Test
    @DisplayName("요구사항1 수강신청하기에 필수적인 요소")
    void changeLectureCapacity() {
        Lecture lecture = new Lecture(1L,"항해",10L,10L);
        given(lectureService.findLectureById(1L)).willReturn(lecture);
        /*
        Long capacity = lecture.getCurrentLectureCapacity() - 1;
        if(capactiry<0){
            throw Exption
        }
        */
        //-1이 나오는지 검토해야함.
        Lecture updateLecture = new Lecture(1L,"항해",10L,9L);
        given(lectureRepository.save(updateLecture)).willReturn(updateLecture);

        Lecture resultLecture = lectureService.changeLectureCapacity(1L);

        assertThat(resultLecture.getCurrentLectureCapacity()).isEqualTo(updateLecture.getCurrentLectureCapacity());
    }
}