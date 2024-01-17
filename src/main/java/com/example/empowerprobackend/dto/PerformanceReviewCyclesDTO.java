package com.example.empowerprobackend.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PerformanceReviewCyclesDTO {
    private Long id;
    private LocalDate cycleStartDate;
    private LocalDate cycleEndDate;
    private String cycleName;

}
