package com.example.special.lecture.application.apply.controller.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApplyRequest {

    private Long userId;

    private Long lectureId;

    private LocalDateTime applyTime;

}
