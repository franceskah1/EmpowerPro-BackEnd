package com.example.empowerprobackend.dto;
import com.example.empowerprobackend.models.LeaveStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LeaveRequestsDTO {
    private Long id;
    private Long userId;
    private String leaveTypes;
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;
    private String notes;
    private String leaveStatus;

}
