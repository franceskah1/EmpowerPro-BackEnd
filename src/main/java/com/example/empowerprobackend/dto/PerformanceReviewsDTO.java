package com.example.empowerprobackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerformanceReviewsDTO {
    private Long id;

    private LocalDate reviewDate;
    private int rating;
    private String feedback;
    private Long userId;
    private Long performanceReviewCyclesId;


}
