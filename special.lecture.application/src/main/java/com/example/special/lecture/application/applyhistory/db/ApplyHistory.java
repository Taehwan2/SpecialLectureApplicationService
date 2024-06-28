package com.example.special.lecture.application.applyhistory.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "apply_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApplyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_hisotry_id")
    private Long applyHistoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "lecture_id", nullable = false)
    private Long lectureId;

    @Column(name = "apply_History_time")
    private LocalDateTime applyHistoryTime;

}
